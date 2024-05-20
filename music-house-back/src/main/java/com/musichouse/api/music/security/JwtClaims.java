package com.musichouse.api.music.security;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtClaims {
    private String id;
    private String role;
    private String name;
    private String lastName;
}
