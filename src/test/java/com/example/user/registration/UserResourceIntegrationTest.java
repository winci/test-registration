package com.example.user.registration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserResourceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_not_get_WelcomeMessage() throws Exception {
        UserData data = new UserData("testuserName", "24.58.0.3", "testPa5%word");
        ObjectMapper mapper = new ObjectMapper();
        String jsonData = mapper.writeValueAsString(data);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/registration")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonData);



        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();
        int status = response.getStatus();

        assertEquals(404, status);

    }

    @Test
    void should_get_WelcomeMessage() throws Exception {
        UserData data = new UserData("testuserName", "24.48.0.3", "testPa5%word");
        ObjectMapper mapper = new ObjectMapper();
        String jsonData = mapper.writeValueAsString(data);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/registration")
                .contentType(MediaType.APPLICATION_JSON).content(jsonData);



        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();
        int status = response.getStatus();

        assertEquals(200, status);

    }

}
