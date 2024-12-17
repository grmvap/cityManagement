package com.example.citymanagement.service.impl;

import com.example.citymanagement.model.Passport;
import com.example.citymanagement.model.Person;
import com.example.citymanagement.repository.PassportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor
@Service
public class PassportService {
    PassportRepository passportRepository;


    public Passport createPasport(Person person) {
        Passport passport = new Passport();
        passport.setPerson(person);
        passport.setNumber(getRandomPasportNumder());
        passport.setDate(LocalDate.now());
        Passport savePassport = passportRepository.save(passport);
        return savePassport;
        //todo написать тест!!!
    }

    public void deletePasport(Long id) {
        passportRepository.deleteById(id);
    }

    static int getRandomPasportNumder() {

        return ThreadLocalRandom.current().nextInt(100000, 1000000);
    }





}
