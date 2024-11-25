package com.example.citymanagement;


import com.example.citymanagement.model.Person;
import com.example.citymanagement.service.LoteryService;
import com.example.citymanagement.service.impl.LoteryServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class CityManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityManagementApplication.class, args);

    }



}
