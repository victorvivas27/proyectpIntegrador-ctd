package com.musichouse.api.music.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@Table(name = "THEMES")
@AllArgsConstructor
@NoArgsConstructor
public class Theme {
    /**
     * Identificador único de la temeatica.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_theme")
    private Long idTheme;

    /**
     * Nombre de la tematica.
     */
    @Column(name = "theme_name", length = 100, unique = true)
    private String themeName;

    /**
     * Descripción de la tematica.
     */
    @Column(name = "description", length = 255)
    private String description;

    /**
     * Anotación que marca el campo como una fecha de creación automática.
     * Hibernate asigna automáticamente la fecha y hora actual al insertar la entidad en la base de datos.
     */
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "regist_date")
    private Date registDate;
}
