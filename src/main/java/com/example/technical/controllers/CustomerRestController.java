package com.example.technical.controllers;

import com.example.technical.models.request.CustomerRequestRemoteObject;
import com.example.technical.models.response.CustomerResponseRemoteObject;
import com.example.technical.services.ICustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/* FILE UserRestController
AUTHOR Guillaume
PROJECT technical
DATE 29/06/2023 */

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/customer/")
public class CustomerRestController implements ICustomerRestController {

    private final ICustomerService userService;

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public CustomerRequestRemoteObject registerCustomer(@Valid @RequestBody CustomerRequestRemoteObject userRequest) {
        return this.userService.registerCustomer(userRequest);
    }

    @GetMapping("{id}")
    @Override
    public CustomerResponseRemoteObject getCustomer(@PathVariable("id") Long id) {
        return this.userService.getCustomer(id);
    }
}

