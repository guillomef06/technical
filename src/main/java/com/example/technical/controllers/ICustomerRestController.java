package com.example.technical.controllers;

/*FILE IUserRestController
        AUTHOR Guillaume
        PROJECT technical
        DATE 29/06/2023 */

import com.example.technical.models.request.CustomerRequestRemoteObject;
import com.example.technical.models.response.CustomerResponseRemoteObject;

/**
 * The interface Customer rest controller.
 */
public interface ICustomerRestController {
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
