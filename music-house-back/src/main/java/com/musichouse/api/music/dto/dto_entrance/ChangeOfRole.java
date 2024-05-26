package com.musichouse.api.music.dto.dto_entrance;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeOfRole {

    @NotNull(message = "El idUser es obligatorio")
    @Positive(message = "El idUser debe ser un número positivo")
    private Long idUser;

    @NotNull(message = "El rol es obligatorio")
    private String rol;
}
