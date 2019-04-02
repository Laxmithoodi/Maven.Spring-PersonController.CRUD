package io.zipcoder.crudapp;


import io.zipcoder.crudapp.model.Person;
import io.zipcoder.crudapp.repositories.PersonRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class TestPersonController {

    @Autowired
    private MockMvc mvc;


    @MockBean
    private PersonRepository repository;


    @Test
    public void testShow() throws Exception {
        int givenId = 0;
        BDDMockito
                .given(repository.findOne(givenId))
                .willReturn(new Person("New", "Person!"));

        String expectedContent = "{\"id\":2,\"firstName\":\"New\",\"lastName\":\"Person!\"}";
        this.mvc.perform(MockMvcRequestBuilders
                .get("/people/" + givenId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }



}
