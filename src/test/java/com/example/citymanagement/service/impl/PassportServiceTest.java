package com.example.citymanagement.service.impl;

import com.example.citymanagement.model.Passport;
import com.example.citymanagement.model.Person;
import com.example.citymanagement.repository.PassportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PassportServiceTest {
    @InjectMocks
    PassportService passportService;
    @Mock
    PassportRepository passportRepository;
    @Mock
    PersonServiceImpl personServiceImpl;

    @Test
    void createPasport() {
        //given
        Person person = new Person();
        person.setName("A");
        Passport passport = new Passport();
        passport.setPerson(person);
        passport.setNumber(PassportService.getRandomPasportNumder());
        passport.setDate(LocalDate.now());
        Mockito.when(passportRepository.save(passport)).thenReturn(passport);
        //when
        Person resultPasport = personServiceImpl.createPerson(person);

        //then
        assertEquals(passport,resultPasport);
        Mockito.verify(passportRepository, Mockito.times(1)).save(passport);


    }
}