package com.example.technical;

import com.example.technical.controllers.CustomerRestController;
import com.example.technical.exceptions.NotFoundException;
import com.example.technical.models.entities.Gender;
import com.example.technical.models.request.CustomerRequestRemoteObject;
import com.example.technical.models.response.CustomerResponseRemoteObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 *
 */
@SpringBootTest(classes = TestApplication.class)
@ExtendWith(SpringExtension.class)
public class CustomerRestControllerIntegrationTest {

    @Autowired
    private CustomerRestController customerRestController;

    private CustomerRequestRemoteObject customerRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customerRequest = new CustomerRequestRemoteObject("userName",
                LocalDate.of(2000, Month.JULY, 3),
                "France",
                "0600000000",
                Gender.FEMALE);
    }

    @Test
    public void contextLoads() {
        assertThat(customerRestController).isNotNull();
    }

    @Test
    public void registerCustomer() {
        CustomerRequestRemoteObject found = customerRestController.registerCustomer(customerRequest);

        assertThat(found).isEqualTo(customerRequest);
    }

    @Test
    public void getCustomer() {
        assertThat(customerRestController.getCustomer(1L)).isNotNull();
    }

    @Test
    public void customerNotFound() {
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> customerRestController.getCustomer(3L));
    }
}
