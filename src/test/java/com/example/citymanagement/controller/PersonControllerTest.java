package com.example.citymanagement.controller;

import com.example.citymanagement.dto.PersonCreateDto;
import com.example.citymanagement.dto.PersonResponseDto;
import com.example.citymanagement.mapper.PersonMapper;
import com.example.citymanagement.model.Person;
import com.example.citymanagement.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @MockBean
    private PersonMapper personMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createPersonTest() throws Exception {
        // Подготовка данных
        PersonCreateDto personCreateDto = new PersonCreateDto();
        Person newPerson = new Person();
        Person createdPerson = new Person();
        PersonResponseDto personResponseDto = new PersonResponseDto();
        // Настройка моков
        when(personMapper.mapToPerson(any(PersonCreateDto.class))).thenReturn(newPerson);
        when(personService.createPerson(any(Person.class))).thenReturn(createdPerson);
        when(personMapper.mapToPersonResponse(any(Person.class))).thenReturn(personResponseDto);
        // Выполнение запроса
        mockMvc.perform(post("/person/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personCreateDto)))
                .andExpect(status().isCreated()) // Ожидаем, что запрос завершится успешно
                .andExpect(content().json(objectMapper.writeValueAsString(personResponseDto))); // Проверяем тело ответа
        // Захват переданных аргументов
        ArgumentCaptor<PersonCreateDto> personCreateCaptor = ArgumentCaptor.forClass(PersonCreateDto.class);
        ArgumentCaptor<Person> personCaptor = ArgumentCaptor.forClass(Person.class);
        ArgumentCaptor<Person> createdPersonCaptor = ArgumentCaptor.forClass(Person.class);
        // Проверка вызовов методов
        verify(personMapper, times(1)).mapToPerson(personCreateCaptor.capture());
        verify(personService, times(1)).createPerson(personCaptor.capture());
        verify(personMapper, times(1)).mapToPersonResponse(createdPersonCaptor.capture());
        // Проверка содержимого захваченного объекта
        assertThat(personCreateCaptor.getValue()).isEqualTo(personCreateDto);
        assertThat(personCaptor.getValue()).isEqualTo(newPerson);
        assertThat(createdPersonCaptor.getValue()).isEqualTo(createdPerson);
    }


    @Test
    void getPersonsTest() throws Exception {
        PersonResponseDto personResponseDto1 = new PersonResponseDto();
        Person person1 = new Person();
        PersonResponseDto personResponseDto2 = new PersonResponseDto();
        Person person2 = new Person();
        List<PersonResponseDto> personResponseDtos = List.of(personResponseDto1, personResponseDto2);
        List<Person> persons = List.of(person1, person2);
        when(personService.getPersons()).thenReturn(persons);
        when(personMapper.mapToPersonResponse(any(Person.class))).thenReturn(personResponseDto1);
        when(personMapper.mapToPersonResponse(any(Person.class))).thenReturn(personResponseDto2);
        mockMvc.perform(get("/person/all")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(personResponseDtos)));
        verify(personService, times(1)).getPersons();
        verify(personMapper, times(1)).mapToPersonResponse(person1);
        verify(personMapper, times(1)).mapToPersonResponse(person2);
    }

    @Test
    void getPersonIdTest() throws Exception {
        Person person = new Person();
        Long personId = 1l;
        PersonResponseDto personResponseDto = new PersonResponseDto();
        person.setId(personId);
        personResponseDto.setId(personId);
        when(personService.getPersonById(personId)).thenReturn(person);
        when(personMapper.mapToPersonResponse(any(Person.class))).thenReturn(personResponseDto);
        mockMvc.perform(get("/person/id/{id}", personId)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(personResponseDto)));
        verify(personService, times(1)).getPersonById(personId);
        verify(personMapper, times(1)).mapToPersonResponse(person);
    }

    @Test
    void getRandomPersonTest() throws Exception {
        Person person = new Person();
        PersonResponseDto personResponseDto = new PersonResponseDto();
        when(personService.getRandomPerson()).thenReturn(person);
        when(personMapper.mapToPersonResponse(any(Person.class))).thenReturn(personResponseDto);
        mockMvc.perform(get("/person/random")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(personResponseDto)));
        verify(personService, times(1)).getRandomPerson();
        verify(personMapper, times(1)).mapToPersonResponse(person);
    }

    @Test
    void deletePersonIdTest() throws Exception {
        Person person = new Person();
        Long personId = 1l;
        person.setId(personId);
        mockMvc.perform(delete("/person/delete/{id}", personId)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(personService, times(1)).deletePersonId(personId);
    }

    @Test
    void updatePersonTest() throws Exception {
        Person person = new Person();
        Long personId = 1l;
        String newName = "newName";
        PersonResponseDto personResponseDto = new PersonResponseDto();
        person.setId(personId);
        person.setName(newName);
        personResponseDto.setId(personId);
        personResponseDto.setName(newName);
        when(personService.updatePerson(personId, newName)).thenReturn(person);
        when(personMapper.mapToPersonResponse(person)).thenReturn(personResponseDto);
        mockMvc.perform(put("/person/update/{id}/{name}", personId, newName)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(personResponseDto)));
        verify(personService, times(1)).updatePerson(personId, newName);
        verify(personMapper, times(1)).mapToPersonResponse(person);
    }
}
//todo поменять статус на криейтет