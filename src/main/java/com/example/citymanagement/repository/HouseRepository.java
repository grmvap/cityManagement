package com.example.citymanagement.repository;

import com.example.citymanagement.model.House;
import com.example.citymanagement.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HouseRepository extends JpaRepository<House, Long> {

    @Query("SELECT DISTINCT p FROM House h JOIN h.persons p WHERE h.street = :street")
    List<Person> findPersonByStreet(@Param("street") String street);
}
