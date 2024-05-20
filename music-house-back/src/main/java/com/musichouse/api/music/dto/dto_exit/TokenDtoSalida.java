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
    private List<Role> roles;
    private String token;
    @Builder.Default
    private String tokenType = "Bearer ";

    // Constructor personalizado que solo toma el token
    public TokenDtoSalida(String token) {
        this.token = token;
        this.tokenType = "Bearer ";
    }

    // Constructor personalizado que toma el token y los roles
    public TokenDtoSalida(String token, List<Role> roles) {
        this.token = token;
        this.tokenType = "Bearer ";
        this.roles = roles;
    }
}