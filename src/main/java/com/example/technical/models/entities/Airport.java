package com.example.technical.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "airports")
public class Airport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "airport_seq")
    @SequenceGenerator(name = "airport_seq",initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "iata_code", nullable = false, unique = true, length = 3)
    private String iataCode;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "global_taxes", nullable = false, precision = 19, scale = 2)
    private BigDecimal globalTaxes;

    @OneToMany(mappedBy = "originAirport", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Flight> flights = new ArrayList<>();
}