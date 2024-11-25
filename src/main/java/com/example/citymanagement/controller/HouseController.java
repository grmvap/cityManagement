package com.example.citymanagement.controller;

import com.example.citymanagement.dto.HouseCreateDto;
import com.example.citymanagement.dto.HouseResponseDto;
import com.example.citymanagement.mapper.HouseMapper;
import com.example.citymanagement.model.House;
import com.example.citymanagement.service.HouseService;
import com.example.citymanagement.service.impl.HouseServiceImpl;
import com.example.citymanagement.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@Tag(name = "house_methods")
@RestController
@RequestMapping("/house")
public class HouseController {

    private PersonService personService;
    private HouseService houseService;
    private HouseMapper houseMapper;

    public HouseController(PersonService personService, HouseService houseService, HouseMapper houseMapper) {
        this.personService = personService;
        this.houseService = houseService;
        this.houseMapper = houseMapper;
    }

    @Operation(summary = "добавляем дом")
    @PostMapping("/create")
    public HouseResponseDto createHouse(@RequestBody HouseCreateDto house) {
        House newHouse = houseMapper.mapToHouse(house);
        House createdHouse = houseService.createHouse(newHouse);
        return houseMapper.mapToHouseResponse(createdHouse);
    }

    @Operation(summary = "получаем дома")
    @GetMapping("/all")
    public List<HouseResponseDto> getHouse() {
        List<House> houseList = houseService.getHouses();
        return houseList.stream().map(houseMapper::mapToHouseResponse).collect(Collectors.toList());

    }

    @Operation(summary = "получаем дом по id")
    @GetMapping("/id/{id}")
    public HouseResponseDto getHouseById(@PathVariable Long id){
        House house = houseService.getHouseById(id);
        return houseMapper.mapToHouseResponse(house);
    }

    @Operation(summary = "удаляем дом по id")
    @DeleteMapping("/delete")
    public void deleteHouseById(@PathParam("id") Long id){
        houseService.deleteHouseId(id);
    }






}

