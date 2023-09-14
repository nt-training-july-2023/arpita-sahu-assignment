package com.nucleusteq.asessmentPlatform.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for security-related beans and settings.
 */
@Configuration
public class SecurityConfig {

    /**
     * Creates a BCrypt password encoder bean.
     *
     * @return A PasswordEncoder instance configured to use BCrypt hashing.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
