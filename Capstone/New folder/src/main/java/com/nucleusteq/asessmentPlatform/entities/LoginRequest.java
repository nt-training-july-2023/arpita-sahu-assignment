package com.nucleusteq.asessmentPlatform.entities;

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
     * The email address of the user for login.
     */
    private String email;

    /**
     * The password of the user for login.
     */
    private String password;
}
