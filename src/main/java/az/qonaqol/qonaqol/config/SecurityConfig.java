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
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorization -> authorization
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/api/gift-card-order/**").permitAll()
                        .requestMatchers("/api/event-view-count/**").permitAll()
                        .requestMatchers("/api/v1/secured").hasRole("USER")
                        .requestMatchers("/api/v1/non-secured").permitAll()
                        .requestMatchers("/api/v1/users/**").hasRole("ADMIN")
                        .requestMatchers("/api/like-event/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/gift-card/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/gift-card/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/gift-card/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/gift-card/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        //.requestMatchers("/api/reservation/{userId}").permitAll()
                        .requestMatchers("/api/reservation/**").permitAll()
                        .requestMatchers("/api/reservation/user/{id}").permitAll()
                        .requestMatchers("/api/reservation/create-reservation-unregistered").permitAll()
                        .requestMatchers("/api/reservation/create-reservation-registered").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/event/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/event/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/event/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/event/**").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-resources/**", "/swagger-ui/**").permitAll())
                .authorizeHttpRequests(auth ->
                        auth.anyRequest().authenticated())
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
