package com.musichouse.api.music.security;

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
                        .requestMatchers(HttpMethod.POST, "api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/user/all").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/user/search/{idUser}").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/user/update").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/user/delete/{idUser}").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/category/all").hasAnyAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/theme/all").hasAnyAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/instrument/all").hasAnyAuthority("USER")
                        .requestMatchers(HttpMethod.POST, "/api/category/**").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/theme/**").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/instrument/**").hasAnyAuthority("ADMIN")

                        .anyRequest().authenticated()
                ).sessionManagement(sessionManager -> sessionManager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(cors -> cors.configurationSource(corsConfigurationSource));
        return http.build();
    }


}
