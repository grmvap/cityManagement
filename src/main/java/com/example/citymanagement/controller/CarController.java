package com.example.citymanagement.controller;

import com.example.citymanagement.dto.CarCreateDto;
import com.example.citymanagement.dto.CarResponseDto;
import com.example.citymanagement.mapper.CarMapper;
import com.example.citymanagement.model.Car;

import com.example.citymanagement.service.CarService;
import com.example.citymanagement.service.impl.CarServiceImpl;

import com.example.citymanagement.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "car_methods")
@RestController
@RequestMapping("/car")
public class CarController {

    private PersonService personService;
    private CarService carService;
    private CarMapper carMapper;



    public CarController(PersonService personService, CarService carService, CarMapper carMapper) {
        this.personService = personService;
        this.carService = carService;
        this.carMapper = carMapper;
    }

    @Operation(summary = "добавляем машину")
    @PostMapping("/create")
    public CarResponseDto createCar(@RequestBody CarCreateDto car) {
        Car newCar = carMapper.mapToCar(car);
        Car createdCar = carService.createCar(newCar);
        return carMapper.mapToCarResponse(createdCar);
    }

    @Operation(summary = "получаем машины")
    @GetMapping("/all")
    public List<CarResponseDto> getCar() {
        List<Car> carList = carService.getCar();
        return carList.stream()
                .map(carMapper::mapToCarResponse)
                .collect(Collectors.toList());
    }

    @Operation(summary = "получаем машину по id")
    @GetMapping("/id/{id}")
    public CarResponseDto getCarById(@PathVariable Long id) {
        Car car = carService.getCarById(id);

        return carMapper.mapToCarResponse(car);
    }
    @Operation(summary = "получаем машины по id жителя")
    @GetMapping("/getCars/{id}")
    public List<CarResponseDto> getPersonsCarById(@PathVariable Long id) {
        List<Car> carList = carService.getPersonsCarById(id);
        return carMapper.mapToCarResponseList(carList);
    }

    @Operation(summary = "удаляем машину по id")
    @DeleteMapping("/delete/{id}")
    public void deleteCarById(@PathVariable Long id) {
        carService.deleteCarById(id);
    }

    @Operation(summary = "обновяем машину по id")
    @PutMapping("/update/{id}/{model}")
    public CarResponseDto updateCar(@PathVariable Long id, @PathVariable String model) {
        Car car = carService.updateCar(id, model);
        return carMapper.mapToCarResponse(car);
    }
}
