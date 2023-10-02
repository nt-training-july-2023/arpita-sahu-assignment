package com.nucleusteq.asessmentPlatform.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private String firstName;

    /**
     * The last name of the user.
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * The email address of the user.
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * The password of the user.
     */
    @Column(nullable = false)
    private String password;

    /**
     * The phone number of the user.
     */
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    /**
     * The role of the user (e.g., 'user', 'admin').
     */
    @Column(columnDefinition = "varchar(255) default 'user'", nullable = false)
    private String role;
}
