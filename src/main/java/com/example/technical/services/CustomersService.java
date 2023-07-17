package com.example.technical.services;

import com.example.technical.config.AppPropertiesResolver;
import com.example.technical.exceptions.BadRequestException;
import com.example.technical.exceptions.NotFoundException;
import com.example.technical.mappers.CustomerMapper;
import com.example.technical.models.entities.Customer;
import com.example.technical.models.request.CustomerRequest;
import com.example.technical.models.response.CustomerResponse;
import com.example.technical.repositories.CustomersRepository;
import lombok.RequiredArgsConstructor;
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
public class CustomersService implements ICustomersService {

    /**
     * Configuration file
     */
    private final AppPropertiesResolver appProperties;

    /**
     * The repository
     */
    private final CustomersRepository customersRepository;

    /**
     * The mapper between DTO and Entities
     */
    private final CustomerMapper customerMapper;

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

    private void isCustomerAnAdult(LocalDate dateOfBirth) {
        if (Period.between(dateOfBirth, LocalDate.now()).getYears() < appProperties.getMinimumAge()) {
            throw new BadRequestException("Customer must be at least " + appProperties.getMinimumAge() + " years old to register");
        }
    }

    private void isCustomerFromCountry(String country) {
        if (!appProperties.getCountry().equalsIgnoreCase(country)) {
            throw new BadRequestException("Customer must be from " + appProperties.getCountry() + " to register");
        }
    }

    private void isCustomerValidPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !isPhoneNumber(phoneNumber)) {
            throw new BadRequestException("Phone number must contain 10 digits");
        }
    }

    private void isCustomerAlreadyRegistered(String userName, LocalDate dateOfBirth) {
        if (this.customersRepository.existsByUserNameAndDateOfBirth(userName, dateOfBirth)) {
            throw new BadRequestException("This customer is already registered");
        }
    }

    private void validateCustomer(CustomerRequest customerRequest) {
        isCustomerAnAdult(customerRequest.getDateOfBirth());
        isCustomerFromCountry(customerRequest.getCountry());
        isCustomerValidPhoneNumber(customerRequest.getPhoneNumber());
        isCustomerAlreadyRegistered(customerRequest.getUserName(), customerRequest.getDateOfBirth());
    }

    /**
     * This method will check if the customer is
     * old enough
     * french
     * if his phone number is valid when sent
     * if he is already registered
     * Then saved if it's a new customer
     * @param customerRequest is the DTO we receive
     * @return CustomerResponse is sent once customer is saved
     */
    @Override
    public CustomerResponse registerCustomer(CustomerRequest customerRequest) {
        validateCustomer(customerRequest);
        Customer newCustomer = customerMapper.mapRequestToEntity(customerRequest);
        newCustomer = this.customersRepository.save(newCustomer);
        return this.customerMapper.mapEntityToResponse(newCustomer);
    }

    /**
     * This method will retrieve the customer with his id
     * @param id used to find the customer
     * @return CustomerResponse when customer is found
     */
    @Override
    @Cacheable("customer")
    public CustomerResponse getCustomer(Long id) {
        Optional<Customer> optCustomer = customersRepository.findById(id);
        if (optCustomer.isEmpty()) {
            throw new NotFoundException("User with id " + id + " was not found");
        }
        return this.customerMapper.mapEntityToResponse(optCustomer.get());
    }

    /**
     * Gets all customers.
     *
     * @return a List of CustomerResponse
     */
    @Override
    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customersRepository.findAll();
        return customers.stream().map(this.customerMapper::mapEntityToResponse).toList();
    }
}
