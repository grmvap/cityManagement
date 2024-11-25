package com.example.citymanagement.service.impl;

import com.example.citymanagement.model.Pasport;
import com.example.citymanagement.model.Person;
import com.example.citymanagement.repository.PasportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PasportServiceTest {
    @InjectMocks
    PasportService pasportService;
    @Mock
    PasportRepository pasportRepository;
    @Mock
    PersonServiceImpl personServiceImpl;

    @Test
    void createPasport() {
        //given
        Person person = new Person();
        person.setName("A");
        Pasport pasport = new Pasport();
        pasport.setPerson(person);
        pasport.setNumber(PasportService.getRandomPasportNumder());
        pasport.setDate(LocalDate.now());
        Mockito.when(pasportRepository.save(pasport)).thenReturn(pasport);
        //when
        Person resultPasport = personServiceImpl.createPerson(person);

        //then
        assertEquals(pasport,resultPasport);
        Mockito.verify(pasportRepository, Mockito.times(1)).save(pasport);


    }
}