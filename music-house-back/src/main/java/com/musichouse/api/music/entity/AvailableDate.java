package com.musichouse.api.music.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;

/**
 * Representa una fecha disponible para un instrumento.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "AVAILABLE_DATES")
public class AvailableDate {
    /**
     * Identificador único de la fecha disponible.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_available_date")
    private Long idAvailableDate;

    /**
     * La fecha para la cual el instrumento está disponible.
     */
    @Column(nullable = false, name = "date_available")
    private LocalDate dateAvailable;

    /**
     * Indica si el instrumento está disponible en la fecha especificada.
     */
    @Column(name = "available", nullable = false)
    private boolean available;

    /**
     * El instrumento asociado con esta fecha disponible.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_instrument")
    @ToString.Exclude
    private Instrument instrument;

    /**
     * La fecha en la que se registró este registro de fecha disponible.
     */
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "regist_date")
    private Date registDate;
}
