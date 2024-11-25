package com.example.citymanagement.repository;

import com.example.citymanagement.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {
}
