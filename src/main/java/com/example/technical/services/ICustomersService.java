package com.example.technical.services;

import com.example.technical.models.request.CustomerRequest;
import com.example.technical.models.response.CustomerResponse;

import java.util.List;

/**
 * The interface Customer service.
 */
public interface ICustomersService {

    /**
     * Register customer customer request remote object.
     *
     * @param userRequest the user request
     * @return the customer response remote object
     */
    CustomerResponse registerCustomer(CustomerRequest userRequest);

    /**
     * Gets customer.
     *
     * @param id the id
     * @return the customer
     */
    CustomerResponse getCustomer(Long id);

    /**
     * Gets all customers.
     *
     * @return a ArrayList of customers
     */
    List<CustomerResponse> getAllCustomers();
}
