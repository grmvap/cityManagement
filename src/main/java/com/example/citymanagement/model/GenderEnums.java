package com.example.citymanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderEnums {
    MALE("мужчина"), FEMALE("женщина"), IT("оно");
    private String gender;
}
