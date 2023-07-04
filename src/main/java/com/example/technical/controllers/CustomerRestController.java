package com.example.technical.controllers;

import com.example.technical.models.request.CustomerRequestRemoteObject;
import com.example.technical.models.response.CustomerResponseRemoteObject;
import com.example.technical.services.ICustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/* FILE UserRestController
AUTHOR Guillaume
PROJECT technical
DATE 29/06/2023 */

/**
 * This REST controller exposes services related to customers
 * at api/v1/customer/
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/customer/")
public class CustomerRestController implements ICustomerRestController {

    /**
     * The service layer where all the business logic is held
     */
    private final ICustomerService userService;


    /**
     * This method is mapped to POST requests to /register
     * @param userRequest is the dto we receive in Json format
     * @return userRequest is sent if the operation is successful
     * with the HTTP status code 201
     */
    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public CustomerRequestRemoteObject registerCustomer(@Valid @RequestBody CustomerRequestRemoteObject userRequest) {
        return this.userService.registerCustomer(userRequest);
    }

    /**
     *
     * @param id is the customer id we get through the path variable
     * @return a CustomerResponseRemoteObject with the HTTP status code 200
     */
    @GetMapping("{id}")
    @Override
    public CustomerResponseRemoteObject getCustomer(@PathVariable("id") Long id) {
        return this.userService.getCustomer(id);
    }
}

