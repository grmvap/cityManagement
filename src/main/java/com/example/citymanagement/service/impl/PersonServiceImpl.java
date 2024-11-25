package com.example.citymanagement.service.impl;

import com.example.citymanagement.aspect.RobinGood;
import com.example.citymanagement.exception.EntityExceptionEnum;
import com.example.citymanagement.exception.EntityNotFoundException;
import com.example.citymanagement.model.Pasport;
import com.example.citymanagement.model.Person;
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
@Primary
public class PersonServiceImpl implements PersonService {


    private PersonRepository personRepository;
    private PasportService pasportService;
    private PersonValidateService personValidateService;

    @Transactional
    public Person createPerson(Person person) {
        if (Objects.isNull(person.getBalance())) {
            person.setBalance(BigDecimal.ZERO);
        }
        personValidateService.validateNewPerson(person);
        Person savedPerson = personRepository.save(person);
        Pasport newPasport = pasportService.createPasport(person);
        savedPerson.setPasport(newPasport);

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
        List<Person> personsList = personRepository.findAll();
        return getPerson(personsList);
    }

    public void deletePersonId(Long id) {
        personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EntityExceptionEnum.PERSON.getEntityException()));
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

}
