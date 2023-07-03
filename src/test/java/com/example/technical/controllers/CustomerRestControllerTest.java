package com.example.technical.controllers;

import com.example.technical.exceptions.*;
import com.example.technical.models.entities.Gender;
import com.example.technical.models.request.CustomerRequestRemoteObject;
import com.example.technical.models.response.CustomerResponseRemoteObject;
import com.example.technical.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/* FILE CustomerRestControllerTest
AUTHOR Guillaume
PROJECT technical
DATE 02/07/2023 */

@WebMvcTest(CustomerRestController.class)
@ActiveProfiles("test")
public class CustomerRestControllerTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    private static CustomerResponseRemoteObject customerResponse;

    private static CustomerRequestRemoteObject customerRequest;

    @BeforeAll
    public static void setupBeforeAll() {
        customerResponse = new CustomerResponseRemoteObject("Alexa",
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

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void registerCustomerCreated() throws Exception {
        Mockito.when(customerService.registerCustomer(customerRequest)).thenReturn(customerRequest);

        mockMvc.perform(post("/api/v1/customer/register")
                        .content(asJsonString(customerRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userName", Matchers.is("Alexa")));
    }

    @Test
    public void registerCustomerTooYoung() throws Exception {
        Mockito.when(customerService.registerCustomer(customerRequest)).thenThrow(TooYoungException.class);

        mockMvc.perform(post("/api/v1/customer/register")
                        .content(asJsonString(customerRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(TooYoungException.class))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void registerCustomerWrongCountry() throws Exception {
        Mockito.when(customerService.registerCustomer(customerRequest)).thenThrow(WrongCountryException.class);

        mockMvc.perform(post("/api/v1/customer/register")
                        .content(asJsonString(customerRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(WrongCountryException.class))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void registerCustomerInvalidPhoneNumber() throws Exception {
        Mockito.when(customerService.registerCustomer(customerRequest)).thenThrow(InvalidPhoneNumberException.class);

        mockMvc.perform(post("/api/v1/customer/register")
                        .content(asJsonString(customerRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(InvalidPhoneNumberException.class))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void registerCustomerAlreadyRegistered() throws Exception {
        Mockito.when(customerService.registerCustomer(customerRequest)).thenThrow(CustomerAlreadyRegisteredException.class);

        mockMvc.perform(post("/api/v1/customer/register")
                        .content(asJsonString(customerRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(CustomerAlreadyRegisteredException.class))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getUserSuccess() throws Exception {
        Mockito.when(customerService.getCustomer(1L)).thenReturn(customerResponse);

        mockMvc.perform(get("/api/v1/customer/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName", Matchers.is("Alexa")));
    }

    @Test
    public void getUserNotFound() throws Exception {
        Mockito.when(customerService.getCustomer(1L)).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/api/v1/customer/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(NotFoundException.class))
                .andExpect(status().isNotFound());
    }
}