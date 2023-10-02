package com.nucleusteq.asessmentPlatform.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data class representing a login request.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    /**
     * The minimum length required for a user's password.
     */
    private static final int MIN_PASSWORD_LENGTH = 6;
    /**
     * The email address of the user for login.
     */
    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@nucleusteq\\.com$",
            message = "Email domain must be @nucleusteq.com")
    private String email;

    /**
     * The password of the user for login.
     */
    @NotBlank(message = "Password is required")
    @Size(min = MIN_PASSWORD_LENGTH,
    message = "Password must be atleast 6 characters long")
    private String password;
}
