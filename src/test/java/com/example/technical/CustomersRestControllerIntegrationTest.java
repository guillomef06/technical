package com.example.technical;

import com.example.technical.controllers.CustomersRestController;
import com.example.technical.exceptions.BadRequestException;
import com.example.technical.exceptions.NotFoundException;
import com.example.technical.models.entities.Gender;
import com.example.technical.models.request.CustomerRequest;
import com.example.technical.models.response.CustomerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest(classes = TestApplication.class)
@ActiveProfiles("test")
class CustomersRestControllerIntegrationTest {

    @Autowired
    private CustomersRestController customersRestController;

    private CustomerRequest customerRequest;

    @BeforeEach
    void setUp() {
        customerRequest = new CustomerRequest("userName",
                LocalDate.of(2000, Month.JULY, 3),
                "France",
                "0600000000",
                Gender.FEMALE);
    }

    @Test
    void contextLoads() {
        assertThat(customersRestController).isNotNull();
    }

    @Test
    void registerCustomer() {
        CustomerResponse found = customersRestController.registerCustomer(customerRequest);

        assertThat(found.getUserName()).isEqualTo(customerRequest.getUserName());
        assertThat(found.getCountry()).isEqualTo(customerRequest.getCountry());
        assertThat(found.getPhoneNumber()).isEqualTo(customerRequest.getPhoneNumber());
        assertThat(found.getDateOfBirth()).isEqualTo(customerRequest.getDateOfBirth());
        assertThat(found.getGender()).isEqualTo(customerRequest.getGender());
    }

    @Test
    void registerCustomerWrongCountry() {
        customerRequest.setCountry("Italia");

        assertThatExceptionOfType(BadRequestException.class).isThrownBy(() -> customersRestController.registerCustomer(customerRequest));
    }

    @Test
    void registerCustomerTooYoung() {
        customerRequest.setDateOfBirth(LocalDate.of(2010,Month.APRIL,10));

        assertThatExceptionOfType(BadRequestException.class).isThrownBy(() -> customersRestController.registerCustomer(customerRequest));
    }

    @Test
    void registerCustomerInvalidPhoneNumber() {
        customerRequest.setPhoneNumber("XXX");

        assertThatExceptionOfType(BadRequestException.class).isThrownBy(() -> customersRestController.registerCustomer(customerRequest));
    }

    @Test
    void registerCustomerAlreadyRegistered() {
        customerRequest.setUserName("hello");
        customerRequest.setDateOfBirth(LocalDate.of(2000, 7,7));
        customerRequest.setCountry("France");

        assertThatExceptionOfType(BadRequestException.class).isThrownBy(() -> customersRestController.registerCustomer(customerRequest));
    }

    @Test
    void getCustomer() {
        assertThat(customersRestController.getCustomer(1L)).isNotNull();
    }

    @Test
    void customerNotFound() {
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> customersRestController.getCustomer(10L));
    }

    @Test
    void getAllCustomers() {
        assertThat(customersRestController.getAllCustomers()).isNotEmpty();
    }
}
