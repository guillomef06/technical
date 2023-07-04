package com.example.technical.repositories;

import com.example.technical.models.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/* FILE IUserRepository
AUTHOR Guillaume
PROJECT technical
DATE 29/06/2023 */

/**
 * This is the repository used for database operations
 * it extends JpaRepository to fully benefit Spring framework
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /**
     * Exists by user name and date of birth boolean.
     *
     * @param userName    the user name
     * @param dateOfBirth the date of birth
     * @return the boolean
     */
    boolean existsByUserNameAndDateOfBirth(String userName, LocalDate dateOfBirth);
}