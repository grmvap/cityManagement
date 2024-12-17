package com.example.citymanagement.controller;

import com.example.citymanagement.dto.CarResponseDto;
import com.example.citymanagement.dto.PersonCreateDto;
import com.example.citymanagement.dto.PersonResponseDto;
import com.example.citymanagement.mapper.CarMapper;
import com.example.citymanagement.mapper.PersonMapper;
import com.example.citymanagement.model.Car;
import com.example.citymanagement.model.Person;
import com.example.citymanagement.service.PersonService;
import com.example.citymanagement.service.impl.LoteryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "person_methods")
@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;
    private PersonMapper personMapper;



    public PersonController(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "добавляем жителя и паспорт")
    @PostMapping("/create")
    public PersonResponseDto createPerson(@RequestBody PersonCreateDto person) {
        Person newPerson = personMapper.mapToPerson(person);
        Person createdPerson = personService.createPerson(newPerson);
        return personMapper.mapToPersonResponse(createdPerson) ;
    }

    @Operation(summary = "получаем жителей")
    @GetMapping("/all")
    public List<PersonResponseDto> getPersons() {
        List<Person> personList = personService.getPersons();
        return personList.stream()
                .map(personMapper::mapToPersonResponse)
                .collect(Collectors.toList());

    }

    @Operation(summary = "получаем жителя по id")
    @GetMapping("/id/{id}")
    public PersonResponseDto getPersonId(@PathVariable Long id) {
        Person person = personService.getPersonById(id);
        return personMapper.mapToPersonResponse(person);
    }


    @Operation(summary = "получаем рандомного жителя")
    @GetMapping("/random")
    public PersonResponseDto getRandomPerson() {
        Person person = personService.getRandomPerson();
        return personMapper.mapToPersonResponse(person);
    }

    @Operation(summary = "удаляем жителя по id")
    @DeleteMapping("/delete/{id}")
    public void deletePersonId(@PathVariable Long id) {

        personService.deletePersonId(id);
    }

    @Operation(summary = "обновяем жителя по id")
    @PutMapping("/update/{id}/{name}")
    public PersonResponseDto updatePerson(@PathVariable Long id, @PathVariable String name) {
        Person person = personService.updatePerson(id, name);
        return personMapper.mapToPersonResponse(person);

    }




}
