package pl.jablonskanycz.bakery.database.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class ClientControllerTest {
    public static final String REQUEST_NEW_CLIENT = """
            {
                "person": {
                    "firstName": "ZOSIA",
                    "lastName": "SAMOSIA"
                },
                "address": {
                    "latitude": 50.6,
                    "longitude": 18.3
                }
            }    
            """
            .trim();
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void shouldAdd() throws Exception {
        //when then
        ResultActions perform = mockMvc.perform(post("/bakery/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(REQUEST_NEW_CLIENT));
        perform.andExpect(status().isCreated())
                .andExpect(jsonPath(".clientId").value(4));
    }

    // TODO I needed to write this test so that the annotations would be already compliant with your next test!
    // so feel free to implement missing tests inside of this below method and add more tests :)
  @Test
  public void yourNexTest() throws Exception {
    //when then

  }
}