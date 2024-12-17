package com.example.citymanagement.repository;

import com.example.citymanagement.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findCarsByPersonId(@Param("personId") Long personId);
}
