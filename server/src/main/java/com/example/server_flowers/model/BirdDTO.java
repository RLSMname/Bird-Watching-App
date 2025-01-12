package com.example.server_flowers.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
public class BirdDTO {

    private String id;
    private String name;
    private String order;
    private String family;
    private String habitat;
    private int sightCount;


    public BirdDTO(String name, String order, String family, String habitat, int sightCount) {
        this.name = name;
        this.order = order;
        this.family = family;
        this.habitat = habitat;
        this.sightCount = sightCount;
    }
}
