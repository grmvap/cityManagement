package com.example.citymanagement.repository;

import com.example.citymanagement.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
