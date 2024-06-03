package com.musichouse.api.music.dto.dto_entrance;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDtoEntrance {

    private Boolean isFavorite;

    @NotNull(message = "El id del user es obligatorio")
    private Long idUser;

    @NotNull(message = "El id del instrumento es obligatorio")
    private Long idInstrument;

}
