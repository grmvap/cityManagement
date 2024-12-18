package com.example.citymanagement.mapper;

import com.example.citymanagement.dto.CarResponseDto;
import com.example.citymanagement.dto.PersonCreateDto;
import com.example.citymanagement.dto.PersonResponseDto;
import com.example.citymanagement.model.Person;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {


    Person mapToPerson(PersonCreateDto personCreateDto);

    @Mapping(target = "carResponseDtos", source = "person", qualifiedByName = "mapToCarResponseDto")
    @Mapping(target = "pasportNumber", source = "pasport.number")
    @Mapping(target = "pasportId", source = "pasport.id")
    PersonResponseDto mapToPersonResponse(Person person);
    @Named("mapToCarResponseDto")
    default List<CarResponseDto> mapToCarResponseDto(Person person){
        return CarMapper.INSTANSE.mapToCarResponseList(person.getCars());
    }
}
