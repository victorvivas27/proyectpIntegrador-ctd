package com.musichouse.api.music.dto.dto_modify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneDtoModify {
    @NotNull(message = "El idPhone es obligatorio")
    @Positive(message = "El idPhone debe ser un número positivo")
    private Long idPhone;

    @NotBlank(message = "El número de teléfono es obligatorio")
    @Pattern(regexp = "^\\+?[0-9]+([-]?[0-9]+)*$", message = "El número de teléfono debe tener un formato válido")
    private String phoneNumber;
}
