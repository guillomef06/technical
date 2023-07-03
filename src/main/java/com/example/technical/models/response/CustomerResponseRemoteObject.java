package com.example.technical.models.response;

import com.example.technical.models.entities.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/* FILE UserResponseRemoteObject
AUTHOR Guillaume
PROJECT technical
DATE 29/06/2023 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponseRemoteObject implements Serializable {

    private String userName;

    private LocalDate dateOfBirth;

    private String country;

    private String phoneNumber;

    private Gender gender;
}
