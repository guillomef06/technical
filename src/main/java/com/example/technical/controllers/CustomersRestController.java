package com.example.technical.controllers;

import com.example.technical.models.request.CustomerRequest;
import com.example.technical.models.response.CustomerResponse;
import com.example.technical.services.ICustomersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * This REST controller exposes services related to customers
 * at api/v1/customer/
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/customers/")
public class CustomersRestController {

    /**
     * The service layer where all the business logic is held
     */
    private final ICustomersService userService;

    /**
     * This method is mapped to POST requests to /register
     * @param customerRequest is the dto we receive in Json format
     * @return userResponse is sent if the operation is successful
     * with the HTTP status code 201
     */
    @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse registerCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        return this.userService.registerCustomer(customerRequest);
    }

    /**
     *
     * @param id is the customer id we get through the path variable
     * @return a CustomerResponse with the HTTP status code 200
     */
    @GetMapping(value = "{id}", produces = APPLICATION_JSON_VALUE)
    public CustomerResponse getCustomer(@PathVariable("id") Long id) {
        return this.userService.getCustomer(id);
    }

    /**
     * Gets all customers.
     *
     * @return a ArrayList of customers
     */
    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public List<CustomerResponse> getAllCustomers() {
        return this.userService.getAllCustomers();
    }
}