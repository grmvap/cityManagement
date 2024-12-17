package com.example.citymanagement.service.impl;

import com.example.citymanagement.aspect.RobinGood;
import com.example.citymanagement.exception.EntityExceptionEnum;
import com.example.citymanagement.exception.EntityNotFoundException;
import com.example.citymanagement.model.*;
import com.example.citymanagement.repository.PersonRepository;
import com.example.citymanagement.service.PersonService;
import com.example.citymanagement.service.PersonValidateService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service("PersonServiceImpl")
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;
    private PassportService passportService;
    private PersonValidateService personValidateService;

    @Transactional
    public Person createPerson(Person person) {
        if (Objects.isNull(person.getBalance())) {
            person.setBalance(BigDecimal.ZERO);
        }
        personValidateService.validateNewPerson(person);
        Person savedPerson = personRepository.save(person);
        Passport newPassport = passportService.createPasport(person);
        savedPerson.setPassport(newPassport);
        savedPerson.setGender(autoGender(savedPerson.getSurname()));

        return savedPerson;
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EntityExceptionEnum.PERSON.getEntityException()));
    }

    @RobinGood
    public Person getRandomPerson() {
        return getPerson(personRepository.findAll());
    }

    @Transactional
    public void deletePersonId(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EntityExceptionEnum.PERSON.getEntityException()));
        for (House house : person.getHouses()) {
            house.getPersons().remove(person);
        }
        personRepository.deleteById(id);
    }

    public Person updatePerson(Long id, String name) {
        Person oldPerson = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EntityExceptionEnum.PERSON.getEntityException()));
        oldPerson.setName(name);
        personRepository.save(oldPerson);
        return oldPerson;
    }

    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }


    public Person getRandomPerson(List<Person> personsList) {
        return getPerson(personsList);
    }

    public Long personCount() {
        return personRepository.count();
    }


    private Person getPerson(List<Person> personsList) {
        if (personsList.isEmpty()) {
            throw new RuntimeException();
        }
        int index = (int) (Math.random() * personsList.size());

        return personsList.get(index);
    }

    private String autoGender(String surname) {
        if (surname.endsWith("ова") || surname.endsWith("ева") ||
                surname.endsWith("ина") || surname.endsWith("ская") ||
                surname.endsWith("цкая")){return GenderEnums.FEMALE.getGender();}
        if (surname.endsWith("ов") || surname.endsWith("ев") ||
                surname.endsWith("ин") || surname.endsWith("ский") ||
                surname.endsWith("цкий")) {return GenderEnums.MALE.getGender();}
        return GenderEnums.IT.getGender();
    }
}
