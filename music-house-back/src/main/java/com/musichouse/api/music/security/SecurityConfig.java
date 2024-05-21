package com.musichouse.api.music.security;

import com.musichouse.api.music.util.RoleConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * hasAnyAuthority:
 * Este método se utiliza para verificar si el usuario actual tiene al menos uno de los permisos especificados en la lista proporcionada.
 * El argumento que recibe es una lista de cadenas que representan los nombres de los permisos.
 * Por ejemplo, hasAnyAuthority('ADMIN', 'USER') verificará si el usuario tiene el permiso ADMIN o USER.
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final CorsConfigurationSource corsConfigurationSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authRequest -> authRequest
                        // Rutas públicas
                        .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                        // Rutas de usuario (todas las operaciones)
                        .requestMatchers("/api/user/**").permitAll()
                        // Rutas de dirección (todas las operaciones)
                        .requestMatchers("/api/address/**").permitAll()
                        // Rutas de teléfono (todas las operaciones)
                        .requestMatchers("/api/phone/**").permitAll()
                        // Rutas de temas (todas las operaciones)
                        .requestMatchers("/api/theme/**").permitAll()
                        // Rutas de categorías (todas las operaciones)
                        .requestMatchers("/api/category/**").permitAll()
                        // Rutas de instrumentos (todas las operaciones)
                        .requestMatchers("/api/instrument/**").permitAll()
                        // Rutas de instrumentos (PUT) solo para ADMIN
                        .requestMatchers(HttpMethod.PUT, "/api/instrument/update").hasAnyAuthority(RoleConstants.ADMIN)
                        // Rutas de URLs de imagen (todas las operaciones)
                        .requestMatchers("/api/imageurls/**").permitAll()

                        .anyRequest().authenticated()
                ).sessionManagement(sessionManager -> sessionManager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(cors -> cors.configurationSource(corsConfigurationSource));
        return http.build();
    }


}
