package com.musichouse.api.music.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * Representa un instrumento musical disponible para alquiler.
 */
@Entity
@Data
@Table(name = "instrument")
@AllArgsConstructor
@NoArgsConstructor
public class Instrument {
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
     * Categoría a la que pertenece el instrumento.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "id_category", referencedColumnName = "id_category", nullable = false)
    private Category category;

    /**
     * Lista de URLs de imágenes asociadas al instrumento.
     */
    @OneToMany(mappedBy = "instrument", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageUrl> imageUrl;
}
