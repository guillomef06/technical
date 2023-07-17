package com.example.technical.services;

import com.example.technical.mappers.FlightMapper;
import com.example.technical.models.entities.Flight;
import com.example.technical.models.response.FlightResponse;
import com.example.technical.repositories.FlightsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The type Flights service.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FlightsService implements IFlightsService {

    /**
     * The repository
     */
    private final FlightsRepository flightsRepository;

    /**
     * The mapper between DTO and Entities
     */
    private final FlightMapper flightMapper;

    /**
     * Gets all flights.
     *
     * @return the all flights
     */
    @Override
    public List<FlightResponse> getAllFlights() {
        List<Flight> flights = flightsRepository.findAll();
        return flights.stream().map(this::calculatePrice).toList();
    }

    /**
     * Gets flights from origin and destination iata codes.
     *
     * @param originIataCode      the origin iata code
     * @param destinationIataCode the destination iata code
     * @param page                the page
     * @return the flights from origin and destination iata codes
     */
    @Override
    public Page<FlightResponse> getFlightsFromOriginAndDestinationIataCodes(String originIataCode, String destinationIataCode, Pageable page) {
        Page<Flight> flights = flightsRepository.findByOriginAirport_IataCodeAndDestinationAirport_IataCodeOrderByDepartureTimeAsc(
                originIataCode,
                destinationIataCode,
                page);
        return new PageImpl<>(flights.stream().map(flightMapper::mapEntityToResponse).toList(), page, flights.getTotalElements());
    }

    /**
     *   Recuperer les taxes des airports
     *   Les aditioner
     *   multiplier le base price par les taxes ie: 100.000 * (1.080 + 1.100)
     *   gonlfer le price selon le nombre de jour restants avant le depart: x * (1 + ((100 - j restants) / 100))
     */
    private FlightResponse calculatePrice(Flight flight) {

        BigDecimal originGlobalTaxes = flight.getOriginAirport().getGlobalTaxes();
        BigDecimal destGlobalTaxes = flight.getDestinationAirport().getGlobalTaxes();
        BigDecimal taxesMult = originGlobalTaxes.add(destGlobalTaxes).add(BigDecimal.valueOf(100L));
        taxesMult = taxesMult.divide(BigDecimal.valueOf(100.00), RoundingMode.HALF_UP);
        long leftDays = Duration.between(LocalDateTime.now(), flight.getDepartureTime()).toDays();
        if (leftDays > 100L) {
            leftDays = 100L;
        }
        BigDecimal periodMult = BigDecimal.valueOf(100L - leftDays,2)
                .add(BigDecimal.valueOf(1.000));
        BigDecimal price = flight.getBasePrice().multiply(taxesMult).multiply(periodMult).setScale(2, RoundingMode.HALF_UP);

        FlightResponse response = flightMapper.mapEntityToResponse(flight);
        response.setPrice(price);
        return response;
    }
}
