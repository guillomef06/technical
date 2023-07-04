package com.example.technical;

import com.example.technical.controllers.CustomerRestController;
import com.example.technical.exceptions.InvalidPhoneNumberException;
import com.example.technical.exceptions.NotFoundException;
import com.example.technical.exceptions.TooYoungException;
import com.example.technical.exceptions.WrongCountryException;
import com.example.technical.models.entities.Gender;
import com.example.technical.models.request.CustomerRequestRemoteObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 *
 */
@SpringBootTest(classes = TestApplication.class)
@ActiveProfiles("test")
class CustomerRestControllerIntegrationTest {

    @Autowired
    private CustomerRestController customerRestController;

    private CustomerRequestRemoteObject customerRequest;

    @BeforeEach
    void setUp() {
        customerRequest = new CustomerRequestRemoteObject("userName",
                LocalDate.of(2000, Month.JULY, 3),
                "France",
                "0600000000",
                Gender.FEMALE);
    }

    @Test
    void contextLoads() {
        assertThat(customerRestController).isNotNull();
    }

    @Test
    void registerCustomer() {
        CustomerRequestRemoteObject found = customerRestController.registerCustomer(customerRequest);

        assertThat(found).isEqualTo(customerRequest);
    }

    @Test
    void registerCustomerWrongCountry() {
        customerRequest.setCountry("Italia");

        assertThatExceptionOfType(WrongCountryException.class).isThrownBy(() -> customerRestController.registerCustomer(customerRequest));
    }

    @Test
    void registerCustomerTooYoung() {
        customerRequest.setDateOfBirth(LocalDate.of(2010,Month.APRIL,10));

        assertThatExceptionOfType(TooYoungException.class).isThrownBy(() -> customerRestController.registerCustomer(customerRequest));
    }

    @Test
    void registerCustomerInvalidPhoneNumber() {
        customerRequest.setPhoneNumber("XXX");

        assertThatExceptionOfType(InvalidPhoneNumberException.class).isThrownBy(() -> customerRestController.registerCustomer(customerRequest));
    }

    @Test
    void getCustomer() {
        assertThat(customerRestController.getCustomer(1L)).isNotNull();
    }

    @Test
    void customerNotFound() {
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> customerRestController.getCustomer(3L));
    }
}
