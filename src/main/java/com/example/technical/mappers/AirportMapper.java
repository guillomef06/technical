package com.example.technical.mappers;

import com.example.technical.models.entities.Airport;
import com.example.technical.models.response.AirportResponseRemoteObject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = UnmappedTargetPolicyConfig.class)
public interface AirportMapper {

    AirportMapper INSTANCE = Mappers.getMapper(AirportMapper.class);

    AirportResponseRemoteObject mapEntityToResponse(Airport airport);
}
