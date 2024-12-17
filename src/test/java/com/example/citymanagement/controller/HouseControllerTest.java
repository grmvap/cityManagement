package com.example.citymanagement.controller;

import com.example.citymanagement.dto.HouseCreateDto;
import com.example.citymanagement.dto.HouseResponseDto;
import com.example.citymanagement.mapper.HouseMapper;
import com.example.citymanagement.mapper.PersonMapper;
import com.example.citymanagement.model.House;
import com.example.citymanagement.service.HouseService;
import com.example.citymanagement.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HouseController.class)
class HouseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HouseService houseService;

    @MockBean
    private HouseMapper houseMapper;

    @MockBean
    private PersonMapper personMapper;

    @MockBean
    private PersonService personService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createHouse() throws Exception {
        House house = new House();
        House createHouse = new House();
        HouseCreateDto houseCreateDto = new HouseCreateDto();
        HouseResponseDto houseResponseDto = new HouseResponseDto();

        when(houseMapper.mapToHouse(houseCreateDto)).thenReturn(house);
        when(houseMapper.mapToHouseResponse(createHouse)).thenReturn(houseResponseDto);
        when(houseService.createHouse(house)).thenReturn(createHouse);

        mockMvc.perform(post("/house/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(houseCreateDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(houseResponseDto)));

        ArgumentCaptor<House> houseCaptor = ArgumentCaptor.forClass(House.class);
        ArgumentCaptor<House> houseArgumentCaptor = ArgumentCaptor.forClass(House.class);
        ArgumentCaptor<HouseCreateDto> createHouseCaptor = ArgumentCaptor.forClass(HouseCreateDto.class);

        verify(houseService,(times(1))).createHouse(houseCaptor.capture());
        verify(houseMapper, (times(1))).mapToHouse(createHouseCaptor.capture());
        verify(houseMapper,times(1)).mapToHouseResponse(houseArgumentCaptor.capture());
        assertThat(houseCaptor.getValue()).isEqualTo(house);
        assertThat(houseArgumentCaptor.getValue()).isEqualTo(createHouse);
        assertThat(createHouseCaptor.getValue()).isEqualTo(houseCreateDto);
    }


    @Test
    void getHouse() {
    }

    @Test
    void getHouseById() {
    }

    @Test
    void deleteHouseById() {
    }

    @Test
    void getPersonByStreet() {
    }
}