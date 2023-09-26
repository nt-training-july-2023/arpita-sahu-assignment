package com.nucleusteq.asessmentPlatform.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a user.
 */
@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * The unique identifier of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private int userId;

    /**
     * The first name of the user.
     */
    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "firstName is required")
    private String firstName;

    /**
     * The last name of the user.
     */
    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "LastName is required")
    private String lastName;

    /**
     * The email address of the user.
     */
    @Column(unique = true, nullable = false)
    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@nucleusteq\\.com$",
    message = "Email domain must be @nucleusteq.com")
    private String email;

    /**
     * The password of the user.
     */
    @Column(nullable = false)
    @NotBlank(message="Password is required")
    @Size(min=6, message = "Password must be atleast 6 characters long")
    private String password;

    /**
     * The phone number of the user.
     */
    @Column(name = "phone_number", nullable = false)
    @Size(min=10, message = "Phone Number must be 10 digit")
    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp = "^[0-9]*$", message = "Phone Number must contain only numeric characters")
    private String phoneNumber;

    /**
     * The role of the user (e.g., 'user', 'admin').
     */
    @Column(columnDefinition = "varchar(255) default 'user'", nullable = false)
    private String role;
}
