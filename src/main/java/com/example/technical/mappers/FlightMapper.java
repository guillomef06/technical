package com.example.technical.mappers;

import com.example.technical.models.entities.Flight;
import com.example.technical.models.response.FlightResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(config = UnmappedTargetPolicyConfig.class, uses = AirportMapper.class)
public interface FlightMapper {

    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    @Mapping(target = "price", ignore = true)
    @Mapping(target = "flightDesignator", expression = "java(flight.getAirlineDesignator().concat(flight.getNumber().toString()))")
    FlightResponse mapEntityToResponse(Flight flight);
}
