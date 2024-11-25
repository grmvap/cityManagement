package com.example.citymanagement.service.impl;

import com.example.citymanagement.model.Car;
import com.example.citymanagement.model.House;
import com.example.citymanagement.model.Person;
import com.example.citymanagement.service.CarService;
import com.example.citymanagement.service.HouseService;
import com.example.citymanagement.service.PersonReferenceService;
import com.example.citymanagement.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor

public class PersonReferenceServiceImpl implements PersonReferenceService {
    private CarService carService;
    private PersonService personService;
    private HouseService houseService;

    public Car addCarToPerson(Long personId, Long carId) {
        Person person = personService.getPersonById(personId);
        Car car = carService.getCarById(carId);
        car.setPerson(person);
        person.getCars().add(car);
        personService.updatePerson(person);

        return carService.updateCar(car);
    }
    public House addHouseToPerson(Long personId, Long houseId){
        Person person = personService.getPersonById(personId);
        House house = houseService.getHouseById(houseId);
        house.getPersons().add(person);
        person.getHouses().add(house);
        personService.updatePerson(person);

        return houseService.updateHouse(house);
    }
}
