package com.example.technical.repositories;

import com.example.technical.models.entities.Customer;
import com.example.technical.models.entities.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testToString() {
        Customer hello = customerRepository.findById(1L).orElseThrow();

        assertThat(hello.toString()).contains("hello");
    }

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