package com.musichouse.api.music.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;


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
     * Fecha y hora de creación de la categoría.
     */
    @Column(name = "creation_date")
    private LocalDate creationDate;

    /**
     * Instrumento al que pertenece la imagen.
     * Esta relación es ManyToOne, lo que significa que muchas imágenes pueden pertenecer a un solo instrumento.
     * FetchType.EAGER indica que la carga de la entidad Instrument se realiza de forma inmediata junto con la carga de ImageUrl.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_instrument")
    @ToString.Exclude
    private Instruments instrument;
    /**
     * Método ejecutado antes de persistir la entidad en la base de datos.
     * Establece la fecha y hora de creación automáticamente.
     */
    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDate.now(); // Capturar la fecha y hora actual correctamente
    }


}
