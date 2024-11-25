package com.example.citymanagement.service.impl;

import com.example.citymanagement.model.Person;
import com.example.citymanagement.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MokPersonServiceImpl")

public class MokPersonServiceImpl implements PersonService {
    @Autowired
    @Lazy
    private PersonServiceImpl personServiceImpl;

    @Override
    public Person createPerson(Person person) {return null;}

    @Override
    public List<Person> getPersons() {
        return null;
    }

    @Override
    public Person getPersonById(Long id) {
        return null;
    }

    @Override
    public void deletePersonId(Long id) {}

    @Override
    public Person updatePerson(Long id, String name) {
        return null;
    }

    @Override
    public Person updatePerson(Person person) {
        return null;
    }

    @Override
    public Person getRandomPerson() {
        return null;
    }

    @Override
    public Long personCount() {
        return null;
    }
}

