package com.musichouse.api.music.dto.dto_entrance;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDtoEntrance {
    @NotBlank(message = "El nombre de la categoría es obligatorio")
    @Size(max = 100, message = "El nombre de la categoría debe tener como máximo {max} caracteres")
    private String categoryName;
    @Size(max = 255, message = "La descripción de la categoría debe tener como máximo {max} caracteres")
    private String description;
}
