package com.example.citymanagement.mapper;

import com.example.citymanagement.dto.CarCreateDto;
import com.example.citymanagement.dto.CarResponseDto;
import com.example.citymanagement.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


import java.util.List;


@Mapper(componentModel = "spring")
public interface CarMapper {
    CarMapper INSTANSE = Mappers.getMapper(CarMapper.class);

    Car mapToCar(CarCreateDto carCreateDto);

    CarResponseDto mapToCarResponse(Car car);

    List<CarResponseDto> mapToCarResponseList(List<Car> carList);

}
