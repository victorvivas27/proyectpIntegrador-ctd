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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ADDRESS")
@EqualsAndHashCode(exclude = {"user"})
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private Long idAddress;

    @Column(name = "street", length = 100, nullable = false)
    private String street;

    @Column(name = "number", length = 100, nullable = false)
    private Long number;

    @Column(name = "city", length = 100, nullable = false)
    private String city;

    @Column(name = "state", length = 100, nullable = false)
    private String state;

    @Column(name = "country", length = 100, nullable = false)
    private String country;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "regist_date")
    private Date registDate;


}


