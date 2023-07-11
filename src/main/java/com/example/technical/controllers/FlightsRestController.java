package com.example.technical.controllers;

import com.example.technical.models.response.FlightResponseRemoteObject;
import com.example.technical.services.IFlightsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/flights/")
public class FlightsRestController {

    private final IFlightsService flightsService;

    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public List<FlightResponseRemoteObject> getAllFlights() {
        return this.flightsService.getAllFlights();
    }
}
