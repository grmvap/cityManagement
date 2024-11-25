package com.example.citymanagement.service;

import com.example.citymanagement.model.Car;

import java.util.List;

public interface CarService {
    Car createCar(Car car);
    List<Car> getCar();
    Car getCarById(Long id);
    void deleteCarById(Long id);

    Car updateCar(Long id, String model);

    Car updateCar(Car car);
}
