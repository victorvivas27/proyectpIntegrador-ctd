package com.musichouse.api.music.dto.dto_entrance;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDtoEntrance {

    @Size(min = 2, max = 100, message = "La calle debe tener entre {min} y {max} caracteres")
    @NotNull(message = "La calle es obligatoria")
    private String street;
    @PositiveOrZero(message = "El número debe ser positivo o cero")
    @NotNull(message = "El número es obligatorio")
    private Long number;

    @Size(min = 2, max = 100, message = "La ciudad debe tener entre {min} y {max} caracteres")
    @NotNull(message = "La ciudad es obligatoria")
    private String city;

    @Size(min = 2, max = 100, message = "El estado debe tener entre {min} y {max} caracteres")
    @NotNull(message = "El estado es obligatorio")
    private String state;

    @Size(min = 2, max = 100, message = "El país debe tener entre {min} y {max} caracteres")
    @NotNull(message = "El país es obligatorio")
    private String country;
}
