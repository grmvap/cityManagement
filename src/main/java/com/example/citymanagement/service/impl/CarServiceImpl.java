package com.example.citymanagement.service.impl;

import com.example.citymanagement.exception.EntityExceptionEnum;
import com.example.citymanagement.exception.EntityNotFoundException;
import com.example.citymanagement.model.Car;
import com.example.citymanagement.repository.CarRepository;
import com.example.citymanagement.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CarServiceImpl")
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;



    @Override
    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getCar() {return carRepository.findAll();}

    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EntityExceptionEnum.CAR.getEntityException()));
    }

    public List<Car> getPersonsCarById(Long personId) {
        return carRepository.findCarsByPersonId(personId);}


    public void deleteCarById(Long id) {
        carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EntityExceptionEnum.CAR.getEntityException()));
        carRepository.deleteById(id);
    }

    public Car updateCar(Long id, String model) {
        Car oldCar = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EntityExceptionEnum.CAR.getEntityException()));
        oldCar.setModel(model);
        carRepository.save(oldCar);
        return oldCar;
    }

    @Override
    public Car updateCar(Car car) {
        return carRepository.save(car);
    }


}
