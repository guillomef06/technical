package com.example.technical.controllers;

import com.example.technical.config.AppPropertiesResolver;
import com.example.technical.exceptions.*;
import com.example.technical.models.entities.Gender;
import com.example.technical.models.request.CustomerRequestRemoteObject;
import com.example.technical.models.response.CustomerResponseRemoteObject;
import com.example.technical.services.CustomerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.technical.config.TestUtils.asJsonString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(value = CustomerRestController.class)
@EnableConfigurationProperties(value = AppPropertiesResolver.class)
@ActiveProfiles("test")
class CustomerRestControllerTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    private static CustomerResponseRemoteObject customerResponse;

    private static CustomerRequestRemoteObject customerRequest;

    @BeforeAll
    static void setupBeforeAll() {
        customerResponse = new CustomerResponseRemoteObject(null,
                "Alexa",
                LocalDate.of(2000, 1,1),
                "France",
                "0600000000",
                Gender.FEMALE);

        customerRequest = new CustomerRequestRemoteObject("Alexa",
                LocalDate.of(2000, 1,1),
                "France",
                "0600000000",
                Gender.FEMALE);
    }

    @Test
    void registerCustomerCreated() throws Exception {
        when(customerService.registerCustomer(customerRequest)).thenReturn(customerResponse);

        mockMvc.perform(post("/api/v1/customers/")
                        .content(asJsonString(customerRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userName").value("Alexa"))
                .andExpect(jsonPath("$.dateOfBirth").value("2000-01-01"))
                .andExpect(jsonPath("$.country").value("France"))
                .andExpect(jsonPath("$.phoneNumber").value("0600000000"))
                .andExpect(jsonPath("$.gender").value("FEMALE"));
    }

    @Test
    void registerCustomerTooYoung() throws Exception {
        when(customerService.registerCustomer(customerRequest)).thenThrow(TooYoungException.class);

        mockMvc.perform(post("/api/v1/customers/")
                        .content(asJsonString(customerRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(TooYoungException.class))
                .andExpect(status().isBadRequest());
    }

    @Test
    void registerCustomerWrongCountry() throws Exception {
        when(customerService.registerCustomer(customerRequest)).thenThrow(WrongCountryException.class);

        mockMvc.perform(post("/api/v1/customers/")
                        .content(asJsonString(customerRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(WrongCountryException.class))
                .andExpect(status().isBadRequest());
    }

    @Test
    void registerCustomerInvalidPhoneNumber() throws Exception {
        when(customerService.registerCustomer(customerRequest)).thenThrow(InvalidPhoneNumberException.class);

        mockMvc.perform(post("/api/v1/customers/")
                        .content(asJsonString(customerRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(InvalidPhoneNumberException.class))
                .andExpect(status().isBadRequest());
    }

    @Test
    void registerCustomerAlreadyRegistered() throws Exception {
        when(customerService.registerCustomer(customerRequest)).thenThrow(CustomerAlreadyRegisteredException.class);

        mockMvc.perform(post("/api/v1/customers/")
                        .content(asJsonString(customerRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(CustomerAlreadyRegisteredException.class))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCustomerSuccess() throws Exception {
        when(customerService.getCustomer(1L)).thenReturn(customerResponse);

        mockMvc.perform(get("/api/v1/customers/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Alexa"))
                .andExpect(jsonPath("$.dateOfBirth").value("2000-01-01"))
                .andExpect(jsonPath("$.country").value("France"))
                .andExpect(jsonPath("$.phoneNumber").value("0600000000"))
                .andExpect(jsonPath("$.gender").value("FEMALE"));
    }

    @Test
    void getCustomerNotFound() throws Exception {
        when(customerService.getCustomer(1L)).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/api/v1/customers/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(NotFoundException.class))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllCustomer() throws Exception {
        List<CustomerResponseRemoteObject> all = new ArrayList<>();
        all.add(customerResponse);
        when(customerService.getAllCustomers()).thenReturn(all);

        mockMvc.perform(get("/api/v1/customers/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}