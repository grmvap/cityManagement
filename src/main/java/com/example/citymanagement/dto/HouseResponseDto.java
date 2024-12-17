package com.example.citymanagement.dto;

import com.example.citymanagement.model.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HouseResponseDto {
    private Long id;
    private String street;
    private Long numberHouse;
    private Person person;
}
