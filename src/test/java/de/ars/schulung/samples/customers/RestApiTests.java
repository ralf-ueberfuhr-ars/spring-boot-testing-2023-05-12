package de.ars.schulung.samples.customers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
  // @WebMvcTest
class RestApiTests {

    // GET /api/v1/customers -> 200 OK + []
    // POST /api/v1/customers mit Customer -> 201 Created + Location Header
    @Autowired
    MockMvc mvc;

    @Test
    void getCustomersShouldReturn200AndEmptyArrayBody() throws Exception {
        mvc.perform(get("/api/v1/customers").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().json("[]"));
    }

    @Test
    void postCustomersShouldReturn201CreatedAndNewCustomer() throws Exception {
        mvc.perform(
            post("/api/v1/customers")
              .accept(MediaType.APPLICATION_JSON)
              .contentType(MediaType.APPLICATION_JSON)
              .content("""
                          {
                              "name": "Tom"
                          }
                """)
          )
          .andExpect(status().isCreated())
          .andExpect(header().exists("Location"))
          .andExpect(jsonPath("name").value("Tom"))
          .andExpect(jsonPath("uuid").exists());
    }

    // TODO uuid in Request-Body soll Fehler bringen
    // TODO POST -> Anlegen -> GET {{Location-Header}} -> Customer als Response

}
