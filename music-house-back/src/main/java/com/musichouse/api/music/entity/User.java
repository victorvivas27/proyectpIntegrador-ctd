package com.musichouse.api.music.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS")
@EqualsAndHashCode(exclude = {"addresses", "phones", "roles"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Address> addresses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<Phone> phones;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Role.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_rol",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Role> roles;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "regist_date")
    private Date registDate;


}
