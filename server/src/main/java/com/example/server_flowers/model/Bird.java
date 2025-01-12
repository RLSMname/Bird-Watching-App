package com.example.server_flowers.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Bird {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String name;
    private String birdOrder;
    private String family;
    private String habitat;
    private int sightCount;


    public Bird(String name, String birdOrder, String family, String habitat, int sightCount) {
        this.name = name;
        this.birdOrder = birdOrder;
        this.family = family;
        this.habitat = habitat;
        this.sightCount = sightCount;
    }
}
