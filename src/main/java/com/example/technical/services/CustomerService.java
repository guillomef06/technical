package com.example.technical.services;

/* FILE UserService
AUTHOR Guillaume
PROJECT technical
DATE 29/06/2023 */

import com.example.technical.config.AppPropertiesResolver;
import com.example.technical.exceptions.*;
import com.example.technical.models.entities.Customer;
import com.example.technical.models.request.CustomerRequestRemoteObject;
import com.example.technical.models.response.CustomerResponseRemoteObject;
import com.example.technical.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * This service hold our business logic
 */
@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    /**
     * Configuration file
     */
    private final AppPropertiesResolver appProperties;

    /**
     * The repository
     */
    private final CustomerRepository customerRepository;

    /**
     * The mapper between DTO and Entities
     */
    private final ModelMapper modelMapper;

    /**
     * Check if the String is a valid phone number
     *
     * @param phoneNumber should be a String that contains 10 digits
     * @return true if phoneNumber is correct, false otherwise
     */
    private boolean isPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        return pattern.matcher(phoneNumber).matches();
    }

    /**
     * This method will check if the customer is
     * old enough
     * french
     * if his phone number is valid when sent
     * if he is already registered
     * Then saved if it's a new customer
     * @param customerRequest is the DTO we receive
     * @return CustomerResponseRemoteObject is sent once customer is saved
     */
    @Override
    public CustomerResponseRemoteObject registerCustomer(CustomerRequestRemoteObject customerRequest) {
        if (Period.between(customerRequest.getDateOfBirth(), LocalDate.now()).getYears() < appProperties.getMinimumAge()) {
            throw new TooYoungException("Customer must be at least " + appProperties.getMinimumAge() + " years old to register");
        }
        if (!appProperties.getCountry().equalsIgnoreCase(customerRequest.getCountry())) {
            throw new WrongCountryException("Customer must be from " + appProperties.getCountry() + " to register");
        }
        if (customerRequest.getPhoneNumber() != null && !isPhoneNumber(customerRequest.getPhoneNumber())) {
            throw new InvalidPhoneNumberException("Phone number must contain 10 digits");
        }
        if (this.customerRepository.existsByUserNameAndDateOfBirth(
                customerRequest.getUserName(),
                customerRequest.getDateOfBirth())) {
            throw new CustomerAlreadyRegisteredException("This customer is already registered");
        }
        Customer newCustomer = this.modelMapper.map(customerRequest, Customer.class);
        newCustomer = this.customerRepository.save(newCustomer);
        return this.modelMapper.map(newCustomer, CustomerResponseRemoteObject.class);
    }

    /**
     * This method will retrieve the customer with his id
     * @param id used to find the customer
     * @return CustomerResponseRemoteObject when customer is found
     */
    @Override
    @Cacheable("customer")
    public CustomerResponseRemoteObject getCustomer(Long id) {
        Optional<Customer> optCustomer = customerRepository.findById(id);
        if (optCustomer.isEmpty()) {
            throw new NotFoundException("User with id " + id + " was not found");
        }
        return this.modelMapper.map(optCustomer.get(), CustomerResponseRemoteObject.class);
    }

    /**
     * Gets all customers.
     *
     * @return a List of CustomerResponseRemoteObject
     */
    @Override
    public List<CustomerResponseRemoteObject> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer ->
                        modelMapper.map(customer, CustomerResponseRemoteObject.class)).toList();
    }
}
