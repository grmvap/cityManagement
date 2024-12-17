package com.example.citymanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@NamedQuery(name = "Car.findCarsByPersonId",
        query = "SELECT c FROM Car c WHERE c.person.id = :personId" )
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue
    private Long id;
    private String model;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;


}
