package de.ars.schulung.samples.customers;

import de.ars.schulung.samples.customers.boundary.Customer;
import de.ars.schulung.samples.customers.domain.CustomerSink;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
  // @WebMvcTest
class RestApiWithMockedRepoTests {

    // GET /api/v1/customers -> 200 OK + []
    // POST /api/v1/customers mit Customer -> 201 Created + Location Header
    @Autowired
    MockMvc mvc;

    @MockBean
    CustomerSink sink;

    @Test
    void getCustomersShouldReturn200AndEmptyArrayBody() throws Exception {
        when(sink.findAll())
          .thenReturn(List.of(new Customer(UUID.fromString("07d29f84-f0c7-11ed-a05b-0242ac120003"), "Tom")));
        mvc.perform(
          get("/api/v1/customers")
            .accept(MediaType.APPLICATION_JSON)
          )
          .andExpect(status().isOk())
          .andExpect(content().json("""
[{
    "uuid": "07d29f84-f0c7-11ed-a05b-0242ac120003",
    "name" : "Tom"
}]"""));
        verify(sink).findAll();
    }

}
