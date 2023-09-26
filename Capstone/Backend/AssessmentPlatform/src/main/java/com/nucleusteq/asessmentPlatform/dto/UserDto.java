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
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@nucleusteq\\.com$",
    message = "Email domain must be @nucleusteq.com")
    private String email;

    /**
     * The password of the user.
     */

    @NotBlank(message="Password is required")
    @Size(min=6, message = "Password must be atleast 6 characters long")
    private String password;

    /**
     * The phone number of the user.
     */

    @Size(min=10, message = "Phone Number must be 10 digit")
    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp = "^[0-9]*$", message = "Phone Number must contain only numeric characters")
    private String phoneNumber;

    /**
     * The role of the user (e.g., admin, user, etc.).
     */
    private String role;
}
