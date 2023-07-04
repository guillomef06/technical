package com.example.technical.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * This is the Customer entity
 * It will be used to store customers into the database
 * <p>
 * Lombok's annotations are used to avoid boilerplate code
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Customer implements Serializable {

    @Id
    @SequenceGenerator(name = "customer_seq",initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "customer_seq")
    private Long id;

    @Column(nullable = false, length = 64)
    private String userName;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;

    @Column(nullable = false, length = 64)
    private String country;

    @Column(length = 10)
    private String phoneNumber;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
}
