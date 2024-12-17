package com.example.citymanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Getter
@Setter
public class CarCreateDto {
    private String model;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CarCreateDto that = (CarCreateDto) o;
        return Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(model);
    }
}
