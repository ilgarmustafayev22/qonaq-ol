package az.qonaqol.qonaqol.config;

import az.qonaqol.qonaqol.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorization -> authorization
                        .requestMatchers("/api/v1/secured").hasRole("USER")
                        .requestMatchers("/api/v1/non-secured").permitAll()
                        .requestMatchers("/api/v1/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/gift-card/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/gift-card/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/gift-card/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/gift-card/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/reservation/create-reservation-unregistered").permitAll()
                        .requestMatchers("/api/reservation/create-reservation-registered").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/event/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/event/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/event/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/event/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/v3/api-docs/**", "/swagger-resources/**", "/swagger-ui/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .build();
    }

}
