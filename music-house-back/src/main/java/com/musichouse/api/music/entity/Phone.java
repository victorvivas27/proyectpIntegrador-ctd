package com.musichouse.api.music.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

/**
 * Representa un teléfono en la aplicación de Music House.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PHONES")

/**
 * La anotación @EqualsAndHashCode(exclude = {"user"}) se utiliza para indicar que, al calcular el hashCode y determinar la igualdad de objetos (equals),
 * se debe excluir el campo "user". Esto se hace para evitar problemas de recursión infinita en las operaciones de igualdad y hashCode.
 */
@EqualsAndHashCode(exclude = {"user"})

public class Phone {
    /**
     * Identificador único para el teléfono.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phone")
    private Long idPhone;

    /**
     * El número de teléfono.
     */
    @Column(name = "phone_number", nullable = false, length = 50)
    private String phoneNumber;

    /**
     * El usuario al que pertenece este teléfono.
     *
     * @ManyToOne: Indica una relación muchos a uno entre la clase Phone y User.
     * Esto significa que múltiples teléfonos pueden pertenecer a un solo usuario.
     * @JoinColumn(name = "user_id"): Especifica la columna en la tabla PHONES que actúa como clave externa para
     * la relación con la tabla USERS (donde se almacenan los usuarios). En este caso, la columna user_id se utiliza
     * para almacenar el identificador único del usuario al que pertenece el teléfono.
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
