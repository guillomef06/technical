package com.example.technical.services;

import com.example.technical.models.request.CustomerRequestRemoteObject;
import com.example.technical.models.response.CustomerResponseRemoteObject;

/**
 * The interface Customer service.
 */
/* FILE IUserService
AUTHOR Guillaume
PROJECT technical
DATE 29/06/2023 */
public interface ICustomerService {

    /**
     * Register customer customer request remote object.
     *
     * @param userRequest the user request
     * @return the customer request remote object
     */
    public CustomerRequestRemoteObject registerCustomer(CustomerRequestRemoteObject userRequest);

    /**
     * Gets customer.
     *
     * @param id the id
     * @return the customer
     */
    public CustomerResponseRemoteObject getCustomer(Long id);
}
