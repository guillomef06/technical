package com.example.technical.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "flights")
public class Flight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "flight_seq")
    @SequenceGenerator(name = "flight_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "number", nullable = false)
    @JdbcTypeCode(SqlTypes.SMALLINT)
    private Short number;

    @Column(name = "airline_designator", nullable = false, length = 2)
    @JdbcTypeCode(SqlTypes.CHAR)
    private String airlineDesignator;

    @Column(name = "base_price", nullable = false, precision = 19, scale = 2)
    private BigDecimal basePrice;

    @Column(name = "departure_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime departureTime;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_airport_id", nullable = false, insertable=false, updatable=false)
    private Airport originAirport;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_airport_id", nullable = false, insertable=false, updatable=false)
    private Airport destinationAirport;
}