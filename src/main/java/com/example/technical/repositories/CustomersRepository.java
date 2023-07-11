package com.example.technical.repositories;

import com.example.technical.models.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * This is the repository used for database operations
 * it extends JpaRepository to fully benefit Spring framework
 */
@Repository
public interface CustomersRepository extends JpaRepository<Customer, Long> {
    /**
     * Exists by username and date of birth boolean.
     *
     * @param userName    the username
     * @param dateOfBirth the date of birth
     * @return the boolean
     */
    boolean existsByUserNameAndDateOfBirth(String userName, LocalDate dateOfBirth);
}