package de.ars.schulung.samples.customers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

@SpringBootTest
@AutoConfigureMockMvc
  // @WebMvcTest
class RestApiWithWebTestClientTests {

    // GET /api/v1/customers -> 200 OK + []
    // POST /api/v1/customers mit Customer -> 201 Created + Location Header
    @Autowired
    MockMvc mvc;
    WebTestClient webTestClient;

    @BeforeEach
    void setup() {
        this.webTestClient = MockMvcWebTestClient
          .bindTo(mvc)
          .build();
    }

    @Test
    void getCustomersShouldReturn200AndEmptyArrayBody() throws Exception {
        webTestClient.get()
          .uri("/api/v1/customers")
          .accept(MediaType.APPLICATION_JSON)
          .exchange()
          .expectStatus().isOk()
          .expectBody().json("[]");
    }

    @Test
    void postCustomersShouldReturn201CreatedAndNewCustomer() throws Exception {
        webTestClient.post()
          .uri("/api/v1/customers")
          .accept(MediaType.APPLICATION_JSON)
          .contentType(MediaType.APPLICATION_JSON)
          .bodyValue(
            """
                {
                  "name": "Tom"
                }
              """)
          .exchange()
          .expectStatus().isCreated()
          .expectHeader().exists("Location")
          .expectBody()
          .jsonPath("name").isEqualTo("Tom")
          .jsonPath("uuid").exists();
    }

    // TODO uuid in Request-Body soll Fehler bringen
    // TODO POST -> Anlegen -> GET {{Location-Header}} -> Customer als Response

}
