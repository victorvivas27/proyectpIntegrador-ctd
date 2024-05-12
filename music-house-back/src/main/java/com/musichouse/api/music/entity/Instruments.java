package com.musichouse.api.music.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un instrumento musical disponible para alquiler.
 */
@Entity
@Data
@Table(name = "INSTRUMENTS")
@AllArgsConstructor
@NoArgsConstructor
public class Instruments {
    /**
     * Identificador único del instrumento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instrument")
    private Long idInstrument;

    /**
     * Nombre del instrumento.
     */
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    /**
     * Descripción detallada del instrumento.
     */
    @Column(name = "description", length = 255)
    private String description;

    /**
     * Precio de alquiler del instrumento.
     * Precision: 10 dígitos.
     * Escala: 2 decimales.
     */
    @Column(name = "rental_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal rentalPrice;
    /**
     * Fecha y hora de creación de la categoría.
     */
    @Column(name = "creation_date")
    private LocalDate creationDate;

    /**
     * Categoría a la que pertenece el instrumento.
     */
    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    /**
     * Lista de URLs de imágenes asociadas al instrumento.
     */

    @OneToMany(mappedBy = "instrument", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ImageUrls> imageUrls = new ArrayList<>();
    /**
     * Método ejecutado antes de persistir la entidad en la base de datos.
     * Establece la fecha y hora de creación automáticamente.
     */
    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDate.now(); // Capturar la fecha y hora actual correctamente
    }
}
