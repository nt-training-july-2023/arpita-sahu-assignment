package com.nucleusteq.asessmentPlatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing a user.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    /**
     * The minimum length required for a user's password.
     */
    private static final int MIN_PASSWORD_LENGTH = 8;
    /**
     * The length required for a user's phone number (number of digits).
     */
    private static final int PHONE_NUMBER_LENGTH = 10;

    /**
     * The unique identifier of the user.
     */
    private int userId;

    /**
     * The first name of the user.
     */
    @NotBlank(message = "firstName is required")
    private String firstName;

    /**
     * The last name of the user.
     */
    @NotBlank(message = "LastName is required")
    private String lastName;

    /**
     * The email address of the user.
     */
    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[a-z][a-zA-Z0-9]*@nucleusteq\\.com$",
    message = "Email must start with a small character "
            + "and shouldn't contain [.-_]")
    private String email;

    /**
     * The password of the user.
     */

    @NotBlank(message = "Password is required")
    @Size(min = MIN_PASSWORD_LENGTH,
    message = "Password must be atleast 8 characters long")
    private String password;

    /**
     * The phone number of the user.
     */

    @Size(min = PHONE_NUMBER_LENGTH, message = "Phone Number must be 10 digit")
    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp = "^[0-9]*$",
    message = "Phone Number must contain only numeric characters")
    private String phoneNumber;

    /**
     * The role of the user (e.g., admin, user, etc.).
     */
    private String role;
}
