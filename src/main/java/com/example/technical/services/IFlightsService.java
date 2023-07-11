package com.example.technical.services;

import com.example.technical.models.response.FlightResponseRemoteObject;

import java.util.List;

public interface IFlightsService {

    List<FlightResponseRemoteObject> getAllFlights();
}
