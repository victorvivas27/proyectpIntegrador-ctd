package com.musichouse.api.music.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * Representa una reserva de un instrumento en Music House.
 * Esta entidad se mapea a la tabla "RESERVATIONS" en la base de datos.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RESERVATIONS")
public class Reservation {

    /**
     * El identificador único para la reserva.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long idReservation;

    /**
     * El usuario que hizo la reserva.
     * Este campo se ignora durante la serialización JSON.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private User user;

    /**
     * El instrumento que ha sido reservado.
     * Este campo se ignora durante la serialización JSON.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_instrument")
    @JsonIgnore
    private Instrument instrument;

    /**
     * La fecha de inicio de la reserva.
     */
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    /**
     * La fecha de fin de la reserva.
     */
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    /**
     * El precio total del alquiler.
     */
    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    /**
     * La fecha de registro de la reserva.
     * Este campo se establece automáticamente cuando se crea la reserva.
     */
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "regist_date")
    private Date registDate;

}
