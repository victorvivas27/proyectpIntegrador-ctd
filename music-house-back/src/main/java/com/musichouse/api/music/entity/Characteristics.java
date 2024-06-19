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
     * Si lleva estuche.
     */
    @Column(name = "instrument_case", length = 50, nullable = false)
    private String instrumentCase;

    /**
     * Si lleva soporte.
     */
    @Column(name = "support", length = 50, nullable = false)
    private String support;

    /**
     * Si lleva afinador.
     */
    @Column(name = "tuner", length = 50, nullable = false)
    private String tuner;

    /**
     * Si lleva micrófono.
     */
    @Column(name = "microphone", length = 50, nullable = false)
    private String microphone;

    /**
     * Si lleva soporte para teléfono.
     */
    @Column(name = "phone_holder", length = 50, nullable = false)
    private String phoneHolder;

}