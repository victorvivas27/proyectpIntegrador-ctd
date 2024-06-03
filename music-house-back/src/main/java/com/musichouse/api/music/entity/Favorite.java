package com.musichouse.api.music.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

/**
 * Representa un elemento favorito en la aplicación de música.
 * Almacena información sobre si un instrumento ha sido marcado como favorito por un usuario.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FAVORITES")
public class Favorite {
    /**
     * El identificador único para el elemento favorito.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_favorite")
    private Long idFavorite;

    /**
     * Indica si el instrumento ha sido marcado como favorito.
     */
    @Column(name = "is_favorite")
    private Boolean isFavorite;

    /**
     * El instrumento asociado con este elemento favorito.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_instrument")
    @JsonIgnoreProperties("favorites")
    private Instrument instrument;

    /**
     * El usuario que marcó el instrumento como favorito.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    @JsonIgnoreProperties("favorites")
    private User user;

    /**
     * La fecha y hora en que se registró este elemento favorito.
     */
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "regist_date")
    private Date registDate;
}
