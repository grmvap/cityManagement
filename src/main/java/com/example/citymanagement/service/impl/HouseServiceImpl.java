package com.example.citymanagement.service.impl;

import com.example.citymanagement.exception.EntityExceptionEnum;
import com.example.citymanagement.exception.EntityNotFoundException;
import com.example.citymanagement.model.House;
import com.example.citymanagement.repository.HouseRepository;
import com.example.citymanagement.service.HouseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("HouseServiceImpl")
@AllArgsConstructor
public class HouseServiceImpl implements HouseService {
    private HouseRepository houseRepository;


    public House createHouse(House house) {
        return houseRepository.save(house);
    }

    public List<House> getHouses() {
        List<House> houseList = houseRepository.findAll();
        return houseList;

    }

    public House getHouseById(Long id) {
        return houseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EntityExceptionEnum.HOUSE.getEntityException()));
    }

    public void deleteHouseId(Long id) {
        houseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(EntityExceptionEnum.HOUSE.getEntityException()));
        houseRepository.deleteById(id);
    }

    public House updateHouse(House house) {
        return houseRepository.save(house);
    }


}



