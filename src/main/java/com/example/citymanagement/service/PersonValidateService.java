package com.example.citymanagement.service;

import com.example.citymanagement.model.Person;
import org.springframework.stereotype.Service;

public interface PersonValidateService {

    void validateNewPerson(Person person);

}
