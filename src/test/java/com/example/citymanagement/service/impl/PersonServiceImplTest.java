package com.example.citymanagement.service.impl;

import com.example.citymanagement.model.Passport;
import com.example.citymanagement.model.Person;
import com.example.citymanagement.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @InjectMocks
    private PersonServiceImpl personServiceImpl;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private PassportService passportService;

    @Test
    void createPerson() {
        //given
        Person person = new Person();
        person.setName("Vasya");
        Passport passport = new Passport();
        Mockito.when(personRepository.save(person)).thenReturn(person);
        Mockito.when(passportService.createPasport(person)).thenReturn(passport);
        //when
        Person resultPerson = personServiceImpl.createPerson(person);
        //then
        assertEquals(passport,resultPerson.getPassport());
        Mockito.verify(personRepository, Mockito.times(1)).save(person);
        Mockito.verify(passportService,Mockito.times(1)).createPasport(person);




    }
}