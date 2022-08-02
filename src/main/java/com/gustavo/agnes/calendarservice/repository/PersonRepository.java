package com.gustavo.agnes.calendarservice.repository;

import com.gustavo.agnes.calendarservice.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Override
    public List<Person> findAll();

    public Optional<Person> findById(Long id);

    public Optional<Person> findByName(String name);
}
