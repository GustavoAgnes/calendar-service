package com.gustavo.agnes.calendarservice.service;

import com.gustavo.agnes.calendarservice.entity.Person;
import com.gustavo.agnes.calendarservice.repository.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonServiceTests {
    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonService personService;

    @DisplayName("Given Valid Request when getAll then should return status 2xx.")
    @Test
    void givenValidRequestWhenGetAllShouldReturn2xx() throws Exception {
        List<Person> getAll = personService.getAll();

        assertThat(getAll).isNotNull();

        verify(personRepository, times(1)).findAll();
    }

    @DisplayName("Given Valid Request when getPersonById then should return status 2xx.")
    @Test
    void shouldGetPersonByIdSuccessfully() throws Exception {
        Optional<Person> person = personService.getPersonById(1L);

        assertThat(person).isNotNull();

        verify(personRepository, times(1)).findById(1L);
    }
}
