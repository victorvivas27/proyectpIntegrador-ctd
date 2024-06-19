package com.musichouse.api.music.dto.dto_exit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDtoExit {
    private Long idReservation;
    private Long idUser;
    private Long idInstrument;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalPrice;
    private String name;
    private String lastName;
    private String email;
    private String city;
    private String country;
    private String instrumentName;
    private String imageUrl;
}
