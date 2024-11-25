package com.example.citymanagement.service;

import com.example.citymanagement.model.House;

import java.util.List;

public interface HouseService {
     House createHouse(House house);

     List<House> getHouses();

     House getHouseById(Long id);

     void deleteHouseId(Long id);

    House updateHouse(House house);
}
