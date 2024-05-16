package com.musichouse.api.music.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

/**
 * Representa una dirección en la aplicación de Music House.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ADDRESS")

/**
 * La anotación @EqualsAndHashCode(exclude = {"user"}) se utiliza para indicar que, al calcular el hashCode y determinar la igualdad de objetos (equals),
 * se debe excluir el campo "user". Esto se hace para evitar problemas de recursión infinita en las operaciones de igualdad y hashCode.
 */
@EqualsAndHashCode(exclude = {"user"})

public class Address {
    /**
     * Identificador único para la dirección.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private Long idAddress;

    /**
     * La calle de la dirección.
     */
    @Column(name = "street", length = 100, nullable = false)
    private String street;

    /**
     * El número de la dirección.
     */
    @Column(name = "number", length = 100, nullable = false)
    private Long number;

    /**
     * La ciudad de la dirección.
     */
    @Column(name = "city", length = 100, nullable = false)
    private String city;

    /**
     * El estado de la dirección.
     */
    @Column(name = "state", length = 100, nullable = false)
    private String state;

    /**
     * El país de la dirección.
     */
    @Column(name = "country", length = 100, nullable = false)
    private String country;

    /**
     * El usuario al que pertenece esta dirección.
     *
     * @ManyToOne: Indica una relación muchos a uno entre la clase Address y User.
     * Esto significa que múltiples direcciones pueden pertenecer a un solo usuario.
     *
     * @JoinColumn(name = "user_id"): Especifica la columna en la tabla ADDRESS que actúa como clave externa para
     * la relación con la tabla USERS (donde se almacenan los usuarios). En este caso, la columna user_id se utiliza
     * para almacenar el identificador único del usuario al que pertenece la dirección.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Anotación que marca el campo como una fecha de creación automática.
     * Hibernate asigna automáticamente la fecha y hora actual al insertar la entidad en la base de datos.
     */
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "regist_date")
    private Date registDate;
}


