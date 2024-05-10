package com.musichouse.api.music.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * Entidad que representa las URLs de las imágenes asociadas a un instrumento.
 */
@Entity
@Data
@Table(name = "IMAGE_URLS")
@AllArgsConstructor
@NoArgsConstructor
public class ImageUrls {
    /**
     * Identificador único de la imagen.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_image")
    private Long idImage;

    /**
     * URL de la imagen.
     */
    @Column(name = "image_url", length = 255)
    private String imageUrl;

    /**
     * Instrumento al que pertenece la imagen.
     * Esta relación es ManyToOne, lo que significa que muchas imágenes pueden pertenecer a un solo instrumento.
     * FetchType.EAGER indica que la carga de la entidad Instrument se realiza de forma inmediata junto con la carga de ImageUrl.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_instrument")
    @ToString.Exclude
    private Instruments instrument;


}
