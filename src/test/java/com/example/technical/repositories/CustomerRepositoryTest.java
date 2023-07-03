package com.example.technical.repositories;

import com.example.technical.models.entities.Customer;
import com.example.technical.models.entities.Gender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/* FILE CustomerRepositoryTest
AUTHOR Guillaume
PROJECT technical
DATE 03/07/2023 */

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testSaveFindExistDelete() {
        Customer customer = new Customer(null,
                "userName",
                LocalDate.now(),
                "Country",
                "0600000000",
                Gender.MALE);

        customerRepository.save(customer);

        assertThat(customerRepository.findAll()).hasSize(3);
        assertThat(customerRepository.existsByUserNameAndDateOfBirth(customer.getUserName(), customer.getDateOfBirth())).isTrue();

        customerRepository.deleteAll();
        assertThat(customerRepository.findAll()).isEmpty();
    }
}