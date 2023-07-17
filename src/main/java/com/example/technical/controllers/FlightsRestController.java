package com.example.technical.controllers;

import com.example.technical.models.response.FlightResponse;
import com.example.technical.services.IFlightsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * The Flights rest controller.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/flights/")
public class FlightsRestController {

    private final IFlightsService flightsService;

    /**
     * Gets all flights.
     *
     * @return a List of flights
     */
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public List<FlightResponse> getAllFlights() {
        return this.flightsService.getAllFlights();
    }

    /**
     * Gets flights from origin and destination iata codes.
     *
     * @param originIataCode      the origin iata code
     * @param destinationIataCode the destination iata code
     * @param page                the page
     * @return the flights from origin and destination iata codes
     */
    @GetMapping(params = {"originIataCode", "destinationIataCode"}, produces = APPLICATION_JSON_VALUE)
    public Page<FlightResponse> getFlightsFromOriginAndDestinationIataCodes(
            @RequestParam(value = "originIataCode", required = true) String originIataCode,
                    @RequestParam(value = "destinationIataCode", required = true) String destinationIataCode,
                    @PageableDefault(size = 5) Pageable page) {
        return this.flightsService.getFlightsFromOriginAndDestinationIataCodes(originIataCode, destinationIataCode, page);
    }
}
