package com.example.citymanagement.service;

import com.example.citymanagement.model.Car;
import com.example.citymanagement.model.Person;
import java.util.List;

public interface PersonService {

    Person createPerson(Person person);
    List<Person> getPersons();
    Person getPersonById(Long id);
    void deletePersonId(Long id);
    Person updatePerson(Long id, String name);
    Person updatePerson(Person person);
    Person getRandomPerson();
    Long personCount();
}
