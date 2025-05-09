package com.example.server_flowers.service;

import com.example.server_flowers.model.Bird;
import com.example.server_flowers.repo.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    @Autowired
    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<Bird> getAllPlants() {
        return plantRepository.findAll();
    }

    public Optional<Bird> getPlantById(String id) {
        return plantRepository.findById(id);
    }

    public Bird addPlant(Bird plant) {
        plant.setId(null);
        return plantRepository.save(plant);
    }

    public Bird updatePlant(Bird plant) {
        String id = plant.getId();
        if (plantRepository.existsById(id)) {
            return plantRepository.save(plant);
        }
        throw new RuntimeException("Plant not found with id " + id);
    }

    public void deletePlant(String id) {
        plantRepository.deleteById(id);
    }
}
