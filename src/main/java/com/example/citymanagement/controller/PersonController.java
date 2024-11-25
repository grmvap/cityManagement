package com.example.citymanagement.controller;

import com.example.citymanagement.dto.PersonCreateDto;
import com.example.citymanagement.dto.PersonResponseDto;
import com.example.citymanagement.mapper.PersonMapper;
import com.example.citymanagement.model.Person;
import com.example.citymanagement.service.PersonService;
import com.example.citymanagement.service.impl.LoteryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "person_methods")
@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;
    private PersonMapper personMapper;
    private LoteryServiceImpl loteryService;

    public PersonController(@Qualifier("PersonServiceImpl") PersonService personService, PersonMapper personMapper, LoteryServiceImpl loteryService) {
        this.personService = personService;
        this.personMapper = personMapper;
        this.loteryService = loteryService;
    }

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

        return personList.stream().map(personMapper::mapToPersonResponse).collect(Collectors.toList());

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
    @DeleteMapping("/delete")
    public void deletePersonId(@PathParam("id") Long id) {
        personService.deletePersonId(id);
    }

    @Operation(summary = "обновяем жителя по id")
    @PutMapping("/update")
    public PersonResponseDto updatePerson(@PathParam("id") Long id, @PathParam("name") String name) {
        Person person = personService.updatePerson(id, name);
        return personMapper.mapToPersonResponse(person);

    }




}
