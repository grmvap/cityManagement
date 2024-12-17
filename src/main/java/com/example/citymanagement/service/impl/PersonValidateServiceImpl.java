package com.example.citymanagement.service.impl;

import com.example.citymanagement.exception.PersonValidateException;
import com.example.citymanagement.model.Person;
import com.example.citymanagement.service.PersonValidateService;
import org.springframework.stereotype.Service;

@Service
public class PersonValidateServiceImpl implements PersonValidateService {
    @Override
    public void validateNewPerson(Person person) {
    int personNameLength = person.getName().length();
    if (personNameLength > 20){
        throw new PersonValidateException("Имя не может быть больше 20-ти символов");
    }
    boolean isHaveDigits = person.getName().matches("/\\b[^\\d\\W]+\\b/g");
    if (isHaveDigits){
        throw new PersonValidateException("Имя содержит только буквы");
    }


    }
}
