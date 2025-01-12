package com.example.server_flowers.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface BirdMapper {
    @Mapping(target = "order", source = "entity.birdOrder")
    BirdDTO birdToBirdDTO(Bird entity);
    @Mapping(target = "birdOrder", source = "dto.order")
    Bird birdDTOtoBird(BirdDTO dto);
}
