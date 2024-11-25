package com.example.citymanagement.aspect;

import com.example.citymanagement.model.Car;
import com.example.citymanagement.model.Person;
import com.example.citymanagement.service.impl.PersonServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class LoteryAspect {
    @Autowired
    private PersonServiceImpl personService;


    @Around(value = "@annotation(com.example.citymanagement.aspect.RobinGood)")
    public Object start(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Person person = (Person) proceedingJoinPoint.proceed();
        log.info("Аспект запущен");
        if (person.getCars().isEmpty()) {
            log.info("в рамках лотереи был выбран пёрсон с id - " + person.getId());
            return person;
        } else {
            log.info("Робин Гуд выбирает другого победителя без машины");
            List<Person> personList = personService.getPersons().stream()
                    .filter(person1 -> person1.getCars().isEmpty()).toList();
            return personService.getRandomPerson(personList);
        }

    }
}



