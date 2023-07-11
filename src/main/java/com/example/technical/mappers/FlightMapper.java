package com.example.technical.mappers;

import com.example.technical.models.entities.Flight;
import com.example.technical.models.response.FlightResponseRemoteObject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(config = UnmappedTargetPolicyConfig.class)
public interface FlightMapper {

    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    @Mapping(target = "price", ignore = true)
    FlightResponseRemoteObject mapEntityToResponse(Flight flight);
}
