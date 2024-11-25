package com.example.citymanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EntityExceptionEnum {
    PERSON("Жилец не найден"), CAR("Автомобиль не найден"), HOUSE("Дом не найден");

    private String entityException;
}


