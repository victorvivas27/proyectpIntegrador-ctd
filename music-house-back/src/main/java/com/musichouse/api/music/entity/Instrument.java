package com.musichouse.api.music.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Representa un instrumento musical disponible para alquiler.
 */
@Entity
@Data
@Table(name = "INSTRUMENTS")
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
    @Column(name = "description", length = 1024)
    private String description;

    /**
     * Peso del instrumento en kilogramos.
     * Precision: 10 dígitos.
     * Escala: 2 decimales.
     */
    @Column(name = "weight", nullable = false, precision = 10, scale = 2)
    private BigDecimal weight;

    /**
     * Altura del instrumento en centímetros.
     */
    @Column(name = "measures", nullable = false, length = 100)
    private String measures;

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
    @ManyToOne
    @JoinColumn(name = "id_category")
    @JsonIgnore
    private Category category;

    /**
     * Tematica  a la que pertenece el instrumento.
     */
    @ManyToOne
    @JoinColumn(name = "id_theme")
    @JsonIgnore
    private Theme theme;

    /**
     * Lista de URLs de imágenes asociadas al instrumento.
     */
    @OneToMany(mappedBy = "instrument", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<ImageUrls> imageUrls = new ArrayList<>();

    /**
     * Características del instrumento.
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "characteristics_id", referencedColumnName = "id_characteristics")
    @JsonIgnore
    private Characteristics characteristics;

    /**
     * Anotación que marca el campo como una fecha de creación automática.
     * Hibernate asigna automáticamente la fecha y hora actual al insertar la entidad en la base de datos.
     */
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "regist_date")
    private Date registDate;
}
