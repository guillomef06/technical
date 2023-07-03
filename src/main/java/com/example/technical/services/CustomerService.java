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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class CustomerService implements ICustomerService {

    private final AppPropertiesResolver appProperties;

    private final CustomerRepository customerRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CustomerService(AppPropertiesResolver appPropertiesResolver,
                           CustomerRepository customerRepository,
                           ModelMapper modelMapper) {
        this.appProperties = appPropertiesResolver;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }


    private boolean isPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        return pattern.matcher(phoneNumber).matches();
    }

    /**
     * @param customerRequest
     */
    @Override
    public CustomerRequestRemoteObject registerCustomer(CustomerRequestRemoteObject customerRequest) {
        if (Period.between(customerRequest.getDateOfBirth(), LocalDate.now()).getYears() < appProperties.getMinimumAge()) {
            throw new TooYoungException("Customer must be at least " + appProperties.getMinimumAge() + " years old to register");
        }
        if (!appProperties.getCountry().equalsIgnoreCase(customerRequest.getCountry())) {
            throw new WrongCountryException("Customer must be from " + appProperties.getCountry() + " to register");
        }
        if (this.customerRepository.existsByUserNameAndDateOfBirth(
                customerRequest.getUserName(),
                customerRequest.getDateOfBirth())) {
            throw new CustomerAlreadyRegisteredException("This customer is already registered");
        }
        if (customerRequest.getPhoneNumber() != null) {
            if (!isPhoneNumber(customerRequest.getPhoneNumber())) {
                throw new InvalidPhoneNumberException("Phone number must contain 10 digits");
            }
        }
        Customer newCustomer = this.modelMapper.map(customerRequest, Customer.class);
        this.customerRepository.save(newCustomer);
        return customerRequest;
    }

    /**
     * @param id
     * @return
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
}
