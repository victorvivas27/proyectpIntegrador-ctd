package com.musichouse.api.music.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
