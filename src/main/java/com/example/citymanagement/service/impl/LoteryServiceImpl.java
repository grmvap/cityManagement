package com.example.citymanagement.service.impl;

import com.example.citymanagement.model.Car;
import com.example.citymanagement.model.Person;
import com.example.citymanagement.repository.PersonRepository;
import com.example.citymanagement.service.LoteryService;
import com.example.citymanagement.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service("LoteryServiceImpl")
@AllArgsConstructor
@Slf4j
public class LoteryServiceImpl implements LoteryService {
    private PersonRepository personRepository;
    private PersonService personService;

    @Scheduled(fixedRate = 5000)
    public void start() {
        log.info("Лотерея началась");
        if (personService.personCount()==0){
            log.info("Жителей для розыгрыша нет");
            return;

        }
        Person person = personService.getRandomPerson();
        BigDecimal oldPersonBalance = person.getBalance();

        BigDecimal newPersonBalance = oldPersonBalance.add(BigDecimal.valueOf(5000));
        person.setBalance(newPersonBalance);
        personRepository.save(person);



    }


}
