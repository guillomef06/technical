package com.example.technical.services;

import com.example.technical.models.request.CustomerRequestRemoteObject;
import com.example.technical.models.response.CustomerResponseRemoteObject;

import java.util.List;

/**
 * The interface Customer service.
 */
public interface ICustomerService {

    /**
     * Register customer customer request remote object.
     *
     * @param userRequest the user request
     * @return the customer response remote object
     */
    CustomerResponseRemoteObject registerCustomer(CustomerRequestRemoteObject userRequest);

    /**
     * Gets customer.
     *
     * @param id the id
     * @return the customer
     */
    CustomerResponseRemoteObject getCustomer(Long id);

    /**
     * Gets all customers.
     *
     * @return a ArrayList of customers
     */
    List<CustomerResponseRemoteObject> getAllCustomers();
}
