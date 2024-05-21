package com.musichouse.api.music.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa las características generales de un instrumento musical.
 */
@Entity
@Data
@Table(name = "CHARACTERISTICS")
@AllArgsConstructor
@NoArgsConstructor
public class Characteristics {
    /**
     * Identificador único para las características del instrumento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_characteristics")
    private Long idCharacteristics;

    /**
     * Material del instrumento (madera, metal, plástico, etc.).
     */
    @Column(name = "material", length = 50, nullable = false)
    private String material;

    /**
     * Número de trastes en el instrumento.
     */
    @Column(name = "frets", nullable = false)
    private Long frets;

    /**
     * Longitud de escala del instrumento.
     */
    @Column(name = "scale_length", length = 50, nullable = false)
    private String scaleLength;

    /**
     * Número de cuerdas en el instrumento.
     */
    @Column(name = "number_of_strings", nullable = false)
    private Long numberOfStrings;

    /**
     * Tipo de cuerdas del instrumento.
     */
    @Column(name = "type_of_strings", length = 50, nullable = false)
    private String typeOfStrings;

    /**
     * País de origen del instrumento.
     */
    @Column(name = "origin_country", length = 50, nullable = false)
    private String originCountry;


}