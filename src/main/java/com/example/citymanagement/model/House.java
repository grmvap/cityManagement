package com.example.citymanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "house")
public class House {
    @Id
    @GeneratedValue
    private Long id;
    private String street;
    private Long numberHouse;
    @ManyToMany
    @JoinTable(name = "person_house", joinColumns = @JoinColumn(name = "house_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> persons;

}
