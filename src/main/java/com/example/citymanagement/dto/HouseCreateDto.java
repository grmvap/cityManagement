package com.example.citymanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class HouseCreateDto {
    private String street;
    private Long numberHouse;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HouseCreateDto that = (HouseCreateDto) o;
        return Objects.equals(street, that.street) && Objects.equals(numberHouse, that.numberHouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, numberHouse);
    }
}
