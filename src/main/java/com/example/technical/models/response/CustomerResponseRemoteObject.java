package com.example.technical.models.response;

import com.example.technical.models.entities.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * This is the DTO we send for the GET request
 * The JsonInclude annotation specify that only
 * non-null attribute will be serialized
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponseRemoteObject implements Serializable {

    private Long id;

    private String userName;

    private LocalDate dateOfBirth;

    private String country;

    private String phoneNumber;

    private Gender gender;
}
