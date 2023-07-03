package com.example.technical.models.request;

import com.example.technical.models.entities.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/* FILE UserRequestRemoteObject
AUTHOR Guillaume
PROJECT technical
DATE 29/06/2023 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerRequestRemoteObject implements Serializable {

    @NotNull(message = "userName must not be null")
    @NotBlank(message = "userName must not be blank")
    @Size(min = 3, message = "userName should be at least 3 characters")
    @Size(max = 255, message = "userName should not exceed 255 characters")
    private String userName;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull(message = "Country must not be null")
    @NotBlank(message = "Country must not be blank")
    @Size(min = 3, message = "Country should be at least 3 characters")
    @Size(max = 255, message = "Country should not exceed 255 characters")
    private String country;

    private String phoneNumber;

    private Gender gender;
}
