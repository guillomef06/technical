package com.example.technical.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightResponse implements Serializable {

    private String flightDesignator;

    private AirportResponse originAirport;

    private AirportResponse destinationAirport;

    private BigDecimal price;

    private LocalDateTime departureTime;
}
