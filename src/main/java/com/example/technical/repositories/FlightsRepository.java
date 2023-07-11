package com.example.technical.repositories;

import com.example.technical.models.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightsRepository  extends JpaRepository<Flight, Long> {
    Flight findFirstByStartAirport_IataCodeOrderByDepartureTimeDesc(@NonNull String iataCode);
}
