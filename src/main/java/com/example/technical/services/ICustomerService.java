package com.example.technical.services;

import com.example.technical.models.request.CustomerRequestRemoteObject;
import com.example.technical.models.response.CustomerResponseRemoteObject;

/* FILE IUserService
AUTHOR Guillaume
PROJECT technical
DATE 29/06/2023 */
public interface ICustomerService {

    public CustomerRequestRemoteObject registerCustomer(CustomerRequestRemoteObject userRequest);

    public CustomerResponseRemoteObject getCustomer(Long id);
}
