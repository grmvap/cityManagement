package com.example.citymanagement.controller;

import com.example.citymanagement.dto.CarResponseDto;
import com.example.citymanagement.dto.HouseResponseDto;
import com.example.citymanagement.mapper.CarMapper;
import com.example.citymanagement.mapper.HouseMapper;
import com.example.citymanagement.model.Car;
import com.example.citymanagement.model.House;
import com.example.citymanagement.service.PersonReferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "reference_methods")
@RestController
@AllArgsConstructor
@RequestMapping("/persons/reference")
public class PersonReferenceController {
    private PersonReferenceService personReferenceService;
    private CarMapper carMapper;
    private HouseMapper houseMapper;

    @Operation(summary = "добавляем машину к жителю")
    @PutMapping("/addCar")
    public CarResponseDto addCarToPerson(@PathParam("personId") Long personId, @PathParam("carId") Long carId) {
        Car car = personReferenceService.addCarToPerson(personId, carId);
        return carMapper.mapToCarResponse(car);
    }

    @Operation(summary = "добавляем дом к жителю")
    @PutMapping("/addHouse")
    public HouseResponseDto addHouseToPerson(@PathParam(("personId")) Long personId, @PathParam(("houseId")) Long houseId){
        House house = personReferenceService.addHouseToPerson(personId, houseId);
        return houseMapper.mapToHouseResponse(house);
    }
}
