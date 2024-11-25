package com.example.citymanagement.service;

import com.example.citymanagement.model.Car;
import com.example.citymanagement.model.House;

public interface PersonReferenceService {
    Car addCarToPerson(Long personId, Long carId);

    House addHouseToPerson(Long personId, Long houseId);
}
