package com.musichouse.api.music.dto.dto_entrance;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoEntrance {

    @NotNull(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+$", message = "El nombre solo debe contener letras, acentos y espacios")
    private String name;

    @NotNull(message = "El apellido es obligatorio")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+$", message = "El apellido solo debe contener letras, acentos y espacios")
    private String lastName;

    @NotNull(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    private String email;

    @NotNull(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @NotNull(message = "El campo addresses debe estar presente y no puede estar vacío")
    @Valid
    private List<AddressDtoEntrance> addresses;

    @Valid
    @NotNull(message = "El campo phones debe estar presente y no puede estar vacío")
    private List<PhoneDtoEntrance> phones;
}
