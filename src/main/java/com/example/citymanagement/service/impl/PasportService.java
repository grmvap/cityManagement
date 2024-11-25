package com.example.citymanagement.service.impl;

import com.example.citymanagement.model.Pasport;
import com.example.citymanagement.model.Person;
import com.example.citymanagement.repository.PasportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor
@Service
public class PasportService {
    PasportRepository pasportRepository;


    public Pasport createPasport(Person person) {
        Pasport pasport = new Pasport();
        pasport.setPerson(person);
        pasport.setNumber(getRandomPasportNumder());
        pasport.setDate(LocalDate.now());
        Pasport savePasport = pasportRepository.save(pasport);
        return savePasport;
        //todo написать тест!!!
    }

    public void deletePasport(Long id) {
        pasportRepository.deleteById(id);
    }

    static int getRandomPasportNumder() {

        return ThreadLocalRandom.current().nextInt(100000, 1000000);
    }





}
