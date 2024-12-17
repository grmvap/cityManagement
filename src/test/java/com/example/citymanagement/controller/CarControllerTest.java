package com.example.citymanagement.controller;

import com.example.citymanagement.dto.CarCreateDto;
import com.example.citymanagement.dto.CarResponseDto;
import com.example.citymanagement.mapper.CarMapper;
import com.example.citymanagement.model.Car;
import com.example.citymanagement.service.CarService;
import com.example.citymanagement.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @MockBean
    private CarMapper carMapper;

    @MockBean
    private PersonService personService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createCarTest() throws Exception {
        // 1. Создаем тестовые данные
        CarCreateDto carCreateDto = new CarCreateDto();
        Car newCar = new Car();
        Car createdCar = new Car();
        CarResponseDto carResponseDto = new CarResponseDto();
        // 2. Настраиваем моки
        when(carMapper.mapToCar(carCreateDto)).thenReturn(newCar);
        when(carService.createCar(newCar)).thenReturn(createdCar);
        when(carMapper.mapToCarResponse(createdCar)).thenReturn(carResponseDto);
        // 3. Выполняем POST-запрос
        mockMvc.perform(post("/car/create")
                        .contentType(MediaType.APPLICATION_JSON) // Указываем тип данных
                        .content(objectMapper.writeValueAsString(carCreateDto))) // Преобразуем DTO в JSON
                .andExpect(status().isOk()) // Проверяем, что статус ответа 200 OK
                .andExpect(content().json(objectMapper.writeValueAsString(carResponseDto)));// Проверяем содержимое ответа

        ArgumentCaptor<Car> carCaptor = ArgumentCaptor.forClass(Car.class);
        ArgumentCaptor<CarCreateDto> carCreateCaptor = ArgumentCaptor.forClass(CarCreateDto.class);
        ArgumentCaptor<Car> createdCarCaptor = ArgumentCaptor.forClass(Car.class);
        // 4. Проверяем, что моки вызваны ожидаемым образом
        verify(carMapper, times(1)).mapToCar(carCreateCaptor.capture());
        verify(carService, times(1)).createCar(carCaptor.capture());
        verify(carMapper, times(1)).mapToCarResponse(createdCarCaptor.capture());
        assertThat(carCaptor.getValue()).isEqualTo(newCar);
        assertThat(carCreateCaptor.getValue()).isEqualTo(carCreateDto);
        assertThat(createdCarCaptor.getValue()).isEqualTo(createdCar);
    }

    @Test
    void getCarTest() throws Exception {
        Car car1 = new Car();
        Car car2 = new Car();
        CarResponseDto carResponseDto1 = new CarResponseDto();
        CarResponseDto carResponseDto2 = new CarResponseDto();
        List<Car> carList = List.of(car1, car2);
        List<CarResponseDto> carResponseDtoList = List.of(carResponseDto1, carResponseDto2);
        when(carService.getCar()).thenReturn(carList);
        when(carMapper.mapToCarResponse(car1)).thenReturn(carResponseDto1);
        when(carMapper.mapToCarResponse(car2)).thenReturn(carResponseDto2);
        mockMvc.perform(get("/car/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(carResponseDtoList)));
        verify(carService, times(1)).getCar();
        verify(carMapper, times(1)).mapToCarResponse(car1);
        verify(carMapper, times(1)).mapToCarResponse(car2);
    }
    @Test
    void getCarIdTest() throws Exception {
        Car car = new Car();
        Long carId = 1L;
        CarResponseDto carResponseDto = new CarResponseDto();
        car.setId(carId);
        carResponseDto.setId(carId);
        when(carService.getCarById(carId)).thenReturn(car);
        when(carMapper.mapToCarResponse(any(Car.class))).thenReturn(carResponseDto);
        mockMvc.perform(get("/car/id/{id}", carId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(carResponseDto)));
        verify(carService, times(1)).getCarById(carId);
        verify(carMapper, times(1)).mapToCarResponse(car);
    }

    @Test
    void getPersonsCarTest() throws Exception {
        Car car1 = new Car();
        Car car2 = new Car();
        Long personId = 1L;
        CarResponseDto carResponseDto1 = new CarResponseDto();
        CarResponseDto carResponseDto2 = new CarResponseDto();
        List<Car> carList = List.of(car1, car2);
        List<CarResponseDto> carResponseDtoList = List.of(carResponseDto1, carResponseDto2);
        when(carService.getPersonsCarById(personId)).thenReturn(carList);
        when(carMapper.mapToCarResponseList(carList)).thenReturn(carResponseDtoList);
        mockMvc.perform(get("/car/getCars/{id}", personId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(carResponseDtoList)));
        verify(carService, times(1)).getPersonsCarById(personId);
        verify(carMapper, times(1)).mapToCarResponseList(carList);
    }

    @Test
    void updateCarTest() throws Exception {
        Car car = new Car();
        Long carId = 1L;
        String model = "Niva";
        CarResponseDto carResponseDto = new CarResponseDto();
        car.setId(carId);
        car.setModel(model);
        carResponseDto.setId(carId);
        carResponseDto.setModel(model);
        when(carService.updateCar(carId, model)).thenReturn(car);
        when(carMapper.mapToCarResponse(car)).thenReturn(carResponseDto);
        mockMvc.perform(put("/car/update/{id}/{model}", carId, model)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(carResponseDto)));
        verify(carService, times(1)).updateCar(carId, model);
        verify(carMapper, times(1)).mapToCarResponse(car);
    }

    @Test
    void deleteCarTest() throws Exception {
        Car car = new Car();
        Long carId = 1L;
        car.setId(carId);
        mockMvc.perform(delete("/car/delete/{id}", carId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(carService,times(1)).deleteCarById(carId);
    }
}

