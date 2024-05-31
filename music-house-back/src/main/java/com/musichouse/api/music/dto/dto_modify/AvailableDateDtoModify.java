package com.musichouse.api.music.dto.dto_modify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AvailableDateDtoModify {
    @NotNull(message = "El idAvailableDate no puede ser nulo")
    private Long idAvailableDate;

    @NotNull(message = "La fecha no puede ser nula")
    @FutureOrPresent(message = "La fecha debe ser en el presente o en el futuro")
    private LocalDate dateAvailable;

    @NotNull(message = "El id del instrumento es obligatorio")
    private Long idInstrument;

    private boolean available;
}
