package com.gustavo.agnes.calendarservice.controller;

import com.gustavo.agnes.calendarservice.entity.Person;
import com.gustavo.agnes.calendarservice.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class PersonController {
    private final PersonService personService;

    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getCandidates(){
        return new ResponseEntity<>(personService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Optional<Person>> getPersonById(@PathVariable(name="personId") Long personId){
        return new ResponseEntity<>(personService.getPersonById(personId), HttpStatus.OK);
    }
}
