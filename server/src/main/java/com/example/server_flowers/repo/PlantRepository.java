package com.example.server_flowers.repo;

import com.example.server_flowers.model.Bird;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends JpaRepository<Bird, String> {

}
