package com.example.technical.services;

import com.example.technical.mappers.FlightMapper;
import com.example.technical.models.entities.Flight;
import com.example.technical.models.response.FlightResponseRemoteObject;
import com.example.technical.repositories.FlightsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightsService implements IFlightsService {

    private final FlightsRepository flightsRepository;

    private final FlightMapper flightMapper;

    @Override
    public List<FlightResponseRemoteObject> getAllFlights() {
        List<Flight> flights = flightsRepository.findAll();
        return flights.stream().map(this::calculatePrice).toList();
    }

    /**
     *   Recuperer les taxes des airports
     *   Les aditioner
     *   multiplier le base price par les taxes ie: 100.000 * (1.080 + 1.100)
     *   gonlfer le price selon le nombre de jour restants avant le depart: x * (1 + ((100 - j restants) / 100))
     */
    private FlightResponseRemoteObject calculatePrice(Flight flight) {

        BigDecimal startGlobalTaxes = flight.getStartAirport().getGlobalTaxes();
        BigDecimal destGlobalTaxes = flight.getDestinationAirport().getGlobalTaxes();
        BigDecimal taxesMult = startGlobalTaxes.add(destGlobalTaxes).add(BigDecimal.valueOf(100L));
        taxesMult = taxesMult.divide(BigDecimal.valueOf(100.00), RoundingMode.HALF_UP);
        long leftDays = Duration.between(LocalDateTime.now(), flight.getDepartureTime()).toDays();
        if (leftDays > 100L) {
            leftDays = 100L;
        }
        BigDecimal periodMult = BigDecimal.valueOf(100L - leftDays,2)
                .add(BigDecimal.valueOf(1.000));
        BigDecimal price = flight.getBasePrice().multiply(taxesMult).multiply(periodMult).setScale(2, RoundingMode.HALF_UP);

        /*
        log.info("Flight base price: {}, taxes mult: {}, left days until flight: {}, period mult: {}, final price: {}",
               flight.getBasePrice(), taxesMult, leftDays, periodMult, price);
        */
        FlightResponseRemoteObject response = this.flightMapper.mapEntityToResponse(flight);
        response.setPrice(price);
        return response;
    }
}
