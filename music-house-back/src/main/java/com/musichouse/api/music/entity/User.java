package com.musichouse.api.music.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Set;

/**
 * Representa una entidad de usuario en la aplicación de Music House.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS")
/**
 * La anotación @EqualsAndHashCode(exclude = {"addresses", "phones", "roles"}) se utiliza para indicar que,
 * al calcular el hashCode y determinar la igualdad de objetos (equals), se deben excluir los campos addresses,
 * phones y roles. Esto se hace para evitar problemas de recursión infinita en las operaciones de igualdad y hashCode.
 */
@EqualsAndHashCode(exclude = {"addresses", "phones", "roles"})
public class User {
    /**
     * Identificador único para el usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    /**
     * El nombre del usuario.
     */
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    /**
     * El apellido del usuario.
     */
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    /**
     * El correo electrónico del usuario (usado para inicio de sesión y notificaciones).
     *
     * Debe ser único en la base de datos para evitar duplicados.
     */
    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    /**
     * La contraseña del usuario (almacenada de forma segura y encriptada).
     */
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    /**
     * Las direcciones del usuario.
     *
     * La propiedad orphanRemoval = true indica que cuando se elimina una dirección del usuario,
     * también se elimina de la base de datos de forma automática para mantener la integridad referencial.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Address> addresses;

    /**
     * Los números de teléfono del usuario.
     *
     * La propiedad orphanRemoval = true indica que cuando se elimina un número de teléfono del usuario,
     * también se elimina de la base de datos de forma automática para mantener la integridad referencial.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Phone> phones;

    /**
     * Roles asignados al usuario.
     *
     * @ManyToMany: Relación muchos a muchos entre User y Role. Un usuario puede tener varios roles y viceversa.
     *
     * @JoinTable: Tabla de unión para la relación, define las columnas user_id y rol_id.
     *
     * fetch = FetchType.EAGER: Carga ansiosa de roles al cargar un usuario.
     *
     * cascade = CascadeType.ALL: Operaciones en cascada para roles asociados (guardar, actualizar, eliminar).
     */
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Role.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_rol",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Role> roles;

    /**
     * Anotación que marca el campo como una fecha de creación automática.
     * Hibernate asigna automáticamente la fecha y hora actual al insertar la entidad en la base de datos.
     */
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "regist_date")
    private Date registDate;
}
