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
public class TokenDtoExit {
    private Long idUser;
    private String name;
    private String lastName;
    private List<Role> roles;
    private String token;
    @Builder.Default
    private String tokenType = "Bearer ";

    public TokenDtoExit(Long idUser, String name, String lastName, List<Role> roles, String token) {
        this.idUser = idUser;
        this.name = name;
        this.lastName = lastName;
        this.roles = roles;
        this.token = token;
        this.tokenType = "Bearer ";
    }
}