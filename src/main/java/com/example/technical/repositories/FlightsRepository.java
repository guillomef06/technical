package com.example.technical.repositories;

import com.example.technical.models.entities.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightsRepository  extends JpaRepository<Flight, Long> {

    Page<Flight> findByOriginAirport_IataCodeAndDestinationAirport_IataCodeOrderByDepartureTimeAsc(@NonNull String startIataCode, @NonNull String destIataCode, Pageable pageable);
}
