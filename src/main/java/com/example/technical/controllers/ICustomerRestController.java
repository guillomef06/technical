package com.example.technical.controllers;

/*FILE IUserRestController
        AUTHOR Guillaume
        PROJECT technical
        DATE 29/06/2023 */

import com.example.technical.models.request.CustomerRequestRemoteObject;
import com.example.technical.models.response.CustomerResponseRemoteObject;

public interface ICustomerRestController {
    public CustomerRequestRemoteObject registerCustomer(CustomerRequestRemoteObject userRequest);

    public CustomerResponseRemoteObject getCustomer(Long id);
}
