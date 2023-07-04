package com.example.technical.services;

import com.example.technical.TestApplication;
import com.example.technical.models.entities.Customer;
import com.example.technical.models.entities.Gender;
import com.example.technical.models.request.CustomerRequestRemoteObject;
import com.example.technical.models.response.CustomerResponseRemoteObject;
import com.example.technical.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/* FILE CustomerServiceTest
AUTHOR Guillaume
PROJECT technical
DATE 03/07/2023 */

@SpringBootTest(classes = TestApplication.class)
@ActiveProfiles("test")
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    @MockBean
    private CustomerRepository customerRepository;

    private  CustomerResponseRemoteObject customerResponse;

    private CustomerRequestRemoteObject customerRequest;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customerResponse = new CustomerResponseRemoteObject(1L,
                "userName",
                LocalDate.of(2000, Month.JULY, 3),
                "France",
                "0600000000",
                Gender.FEMALE);

        customer = modelMapper.map(customerResponse, Customer.class);
        customerRequest = modelMapper.map(customerResponse, CustomerRequestRemoteObject.class);
    }

    @Test
    void testRegisterCustomer() {
        when(customerRepository.existsByUserNameAndDateOfBirth(anyString(), any())).thenReturn(false);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerResponseRemoteObject result = customerService.registerCustomer(customerRequest);
        assertEquals(customerResponse.getUserName(), result.getUserName());
    }

    @Test
    void testGetCustomer() {
        when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(customer));
        CustomerResponseRemoteObject result = customerService.getCustomer(1L);
        assertEquals(customerResponse, result);
    }
}