package com.example.citymanagement.mapper;

import com.example.citymanagement.dto.CarResponseDto;
import com.example.citymanagement.dto.HouseCreateDto;
import com.example.citymanagement.dto.HouseResponseDto;
import com.example.citymanagement.dto.PersonResponseDto;
import com.example.citymanagement.model.House;
import com.example.citymanagement.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HouseMapper {
    HouseMapper INSTANSE = Mappers.getMapper(HouseMapper.class);

    House mapToHouse(HouseCreateDto houseCreateDto);

    HouseResponseDto mapToHouseResponse(House house);


}
