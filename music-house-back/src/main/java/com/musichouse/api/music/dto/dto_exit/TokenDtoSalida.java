package com.musichouse.api.music.dto.dto_exit;

import com.musichouse.api.music.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDtoSalida {
    private String name;
    private String lastName;
    private List<Role> roles;
    private String token;
    @Builder.Default
    private String tokenType = "Bearer ";

    // Constructor personalizado que toma el nombre del usuario, apellido, roles y token
    public TokenDtoSalida(String name, String lastName, List<Role> roles, String token) {
        this.name = name;
        this.lastName = lastName;
        this.roles = roles;
        this.token = token;
        this.tokenType = "Bearer ";
    }
}