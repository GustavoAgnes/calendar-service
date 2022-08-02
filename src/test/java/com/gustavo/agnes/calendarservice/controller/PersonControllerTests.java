package com.gustavo.agnes.calendarservice.controller;

import com.gustavo.agnes.calendarservice.entity.Person;
import com.gustavo.agnes.calendarservice.service.PersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = PersonController.class)
class PersonControllerTests {
    public static final String USERS_API = "/api/v1/users";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;


    @DisplayName("Given Valid Request when getAll then should return status 2xx.")
    @Test
    void givenValidRequestWhenGetAllShouldReturn2xx() throws Exception {
        mockMvc
                .perform(get(USERS_API)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        verify(personService, times(1)).getAll();
    }

    @DisplayName("Given Valid Request when getPersonById then should return status 2xx.")
    @Test
    void givenValidRequestWhenGetPersonByIdShouldReturnOnePerson() throws Exception {
        Person person = Person.builder().personId(1L).name("teste").build();

        mockMvc
                .perform(get(USERS_API + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("personId","1"))
                .andExpect(status().is2xxSuccessful());

        verify(personService, times(1)).getPersonById(1L);
    }
}
