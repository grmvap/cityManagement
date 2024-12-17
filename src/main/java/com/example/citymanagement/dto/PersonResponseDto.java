package com.example.citymanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonResponseDto {
    private Long passportId;
    private Long passportNumber;
    private String name;
    private String surname;
    private String gender;
    private Long id;
    private Long balance;
    private List<CarResponseDto> carResponseDtos;
}
