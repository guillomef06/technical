package com.example.technical.mappers;

import com.example.technical.models.entities.Airport;
import com.example.technical.models.response.AirportResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = UnmappedTargetPolicyConfig.class)
public interface AirportMapper {

    AirportMapper INSTANCE = Mappers.getMapper(AirportMapper.class);

    AirportResponse mapEntityToResponse(Airport airport);
}
