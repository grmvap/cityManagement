package com.example.citymanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonResponseDto {
    private Long pasportId;
    private Long pasportNumber;
    private String name;
    private Long id;
    private Long balance;

    private List<CarResponseDto> carResponseDtos;
}
