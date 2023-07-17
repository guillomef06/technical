package com.example.technical.services;

import com.example.technical.TestApplication;
import com.example.technical.mappers.CustomerMapper;
import com.example.technical.models.entities.Customer;
import com.example.technical.models.entities.Gender;
import com.example.technical.models.request.CustomerRequest;
import com.example.technical.models.response.CustomerResponse;
import com.example.technical.repositories.CustomersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = TestApplication.class)
@ActiveProfiles("test")
class CustomersServiceTest {

    @Autowired
    private CustomersService customersService;

    @Autowired
    private CustomerMapper customerMapper;

    @MockBean
    private CustomersRepository customersRepository;

    private CustomerResponse customerResponse;

    private CustomerRequest customerRequest;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customerRequest = new CustomerRequest("userName",
                LocalDate.of(2000, Month.JULY, 3),
                "France",
                "0600000000",
                Gender.FEMALE);

        customer = customerMapper.mapRequestToEntity(customerRequest);
        customerResponse = customerMapper.mapEntityToResponse(customer);
    }

    @Test
    void testRegisterCustomer() {
        when(customersRepository.existsByUserNameAndDateOfBirth(anyString(), any())).thenReturn(false);
        when(customersRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerResponse result = customersService.registerCustomer(customerRequest);
        assertEquals(customerResponse.getUserName(), result.getUserName());
    }

    @Test
    void testGetCustomer() {
        when(customersRepository.findById(1L)).thenReturn(Optional.ofNullable(customer));
        CustomerResponse result = customersService.getCustomer(1L);
        assertEquals(customerResponse, result);
    }
}