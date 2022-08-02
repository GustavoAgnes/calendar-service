package com.gustavo.agnes.calendarservice.service;

import com.gustavo.agnes.calendarservice.entity.Person;
import com.gustavo.agnes.calendarservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> getPersonById(Long id){
        return personRepository.findById(id);
    }

    public List<Person> getAll(){
        return personRepository.findAll();
    }
}
