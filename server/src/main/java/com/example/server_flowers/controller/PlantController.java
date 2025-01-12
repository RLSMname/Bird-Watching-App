package com.example.server_flowers.controller;

import com.example.server_flowers.PlantWebSocketHandler;
import com.example.server_flowers.model.Bird;
import com.example.server_flowers.model.BirdDTO;
import com.example.server_flowers.model.BirdMapper;
import com.example.server_flowers.service.PlantService;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plants")
public class PlantController {

    private final PlantService plantService;
    private final PlantWebSocketHandler plantWebSocketHandler;
    private BirdMapper birdMapper;

    private static final Logger logger = LoggerFactory.getLogger(PlantController.class);

    @Autowired
    public PlantController(PlantService plantService, PlantWebSocketHandler plantWebSocketHandler) {
        this.plantService = plantService;
        this.plantWebSocketHandler = plantWebSocketHandler;
        this.birdMapper = Mappers.getMapper(BirdMapper.class);
    }

    // Get all plants
    @GetMapping
    public ResponseEntity<List<BirdDTO>> getAllPlants() {
        logger.info("Request to get all birds");
        return ResponseEntity.status(HttpStatus.OK).body(plantService.getAllPlants().stream().map(bird -> birdMapper.birdToBirdDTO(bird)).toList());
    }

    // Get a specific plant by ID
    @GetMapping("/{id}")
    public ResponseEntity<BirdDTO> getPlantById(@PathVariable String id) {
        logger.info("Request to get bird with ID: {}", id);
        Optional<Bird> bird = plantService.getPlantById(id);
        return bird.map(b ->
                        {
                            logger.info("Bird found: {}", b);
                            return ResponseEntity.ok(birdMapper.birdToBirdDTO(b));
                        }
                )  // Map Bird to BirdDTO
                .orElseGet(() -> {
                    logger.warn("Plant with ID: {} not found", id);
                    return ResponseEntity.notFound().build();
                });
    }

    // Add a new plant
    @PostMapping
    public ResponseEntity<BirdDTO> addPlant(@RequestBody BirdDTO birdDTO, @RequestHeader("session-id") String sessionId) {
        logger.info("Request to add a new bird with name: {} and session-id: {}", birdDTO.getName(), sessionId);
        Bird savedBird = plantService.addPlant(birdMapper.birdDTOtoBird(birdDTO));
        sendMessage("CREATE", savedBird, sessionId);
        logger.info("Added new bird with name: {} and session-id: {}", birdDTO.getName(), sessionId);
        return ResponseEntity.status(HttpStatus.OK).body(birdMapper.birdToBirdDTO(savedBird));
    }

    // Update an existing plant
    @PutMapping
    public ResponseEntity<BirdDTO> updatePlant(@RequestBody BirdDTO birdDTO, @RequestHeader("session-id") String sessionId) {
        logger.info("Request to update bird with name: {} and session-id: {}", birdDTO.getName(), sessionId);
        try {
            Bird updatedBird = plantService.updatePlant(birdMapper.birdDTOtoBird(birdDTO));
            sendMessage("UPDATE", updatedBird, sessionId);
            logger.info("Updated bird with name: {} and session-id: {}", birdDTO.getName(), sessionId);
            return ResponseEntity.ok(birdMapper.birdToBirdDTO(updatedBird));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Delete a plant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable String id, @RequestHeader("session-id") String sessionId) {
        logger.info("Request to delete bird with id: {} and session-id: {}", id, sessionId);
        try {
            plantService.deletePlant(id);
            sendMessage("DELETE", id, sessionId);
            logger.info("Deleted bird with id: {} and session-id: {}", id, sessionId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (RuntimeException e) {
            logger.info("Didn't find bird with id: {} and session-id: {}", id, sessionId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private void sendMessage(String type, Object data, String sessionId) {
        String message = createMessage(type, data, sessionId);
        plantWebSocketHandler.broadcast(message);
    }

    private String createMessage(String type, Object data, String sessionId) {
        return String.format("{\"type\":\"%s\",\"data\":\"%s\",\"senderSessionId\":\"%s\"}", type, data, sessionId);
    }
}
