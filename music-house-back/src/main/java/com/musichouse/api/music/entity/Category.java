package com.musichouse.api.music.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entidad que representa una categoría de música.
 */
@Entity
@Data
@Table(name = "CATEGORY")
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    /**
     * Identificador único de la categoría.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long idCategory;

    /**
     * Nombre de la categoría.
     */
    @Column(name = "category_name", length = 100, unique = true)
    private String categoryName;

    /**
     * Descripción de la categoría.
     */
    @Column(name = "description", length = 255)
    private String description;

    /**
     * Fecha y hora de creación de la categoría.
     */
    @Column(name = "creation_date")
    private LocalDate creationDate;

    /**
     * Método ejecutado antes de persistir la entidad en la base de datos.
     * Establece la fecha y hora de creación automáticamente.
     */
    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDate.now(); // Capturar la fecha y hora actual correctamente
    }


}
