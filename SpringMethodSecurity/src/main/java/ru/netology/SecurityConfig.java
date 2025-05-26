package ru.netology;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        // InMemoryUserDetailsManager setup with two users
        UserDetails admin = User.withUsername("Admin")
                .password(encoder.encode("123"))  // <-- Encode the password
                .roles("READ", "WRITE", "DELETE")
                .build();

        UserDetails user = User.withUsername("User1")
                .password(encoder.encode("123"))  // <-- Encode the password
                .roles("READ")
                .build();
        UserDetails user2 = User.withUsername("User2")
                .password(encoder.encode("123"))
                .roles("WRITE")
                .build();
        UserDetails user3 = User.withUsername("User3")
                .password(encoder.encode("123"))
                .roles("DELETE")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/persons/by-city").hasRole("WRITE")
                        .requestMatchers("/persons/by-name-surname").hasRole("READ")
                        .requestMatchers("/persons/by-age").hasRole("DELETE")
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
