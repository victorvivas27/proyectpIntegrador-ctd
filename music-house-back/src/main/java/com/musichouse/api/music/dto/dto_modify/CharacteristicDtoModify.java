package com.musichouse.api.music.dto.dto_modify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacteristicDtoModify {

    @NotNull(message = "El ID de las características es obligatorio")
    private Long idCharacteristic;

    @NotBlank(message = "El material es obligatorio")
    @Size(max = 50, message = "El material no puede tener más de {max} caracteres")
    private String material;


    @NotNull(message = "El número de trastes es obligatorio")
    @Min(value = 0, message = "El número de trastes debe ser un valor positivo")
    private Long frets;


    @NotBlank(message = "La longitud de la escala es obligatoria")
    @Size(max = 50, message = "La longitud de la escala no puede tener más de {max} caracteres")
    private String scaleLength;


    @NotNull(message = "El número de cuerdas es obligatorio")
    @Min(value = 0, message = "El número de cuerdas debe ser un valor positivo")
    private Long numberOfStrings;


    @NotBlank(message = "El tipo de cuerdas es obligatorio")
    @Size(max = 50, message = "El tipo de cuerdas no puede tener más de {max} caracteres")
    private String typeOfStrings;


    @NotBlank(message = "El país de origen es obligatorio")
    @Size(max = 50, message = "El país de origen no puede tener más de 50 caracteres")
    private String originCountry;
}
