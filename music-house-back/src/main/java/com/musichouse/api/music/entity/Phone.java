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
@Table(name = "PHONES")
@EqualsAndHashCode(exclude = {"user"})
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phone")
    private Long idPhone;

    @Column(name = "phone_number", nullable = false,length = 50)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "regist_date")
    private Date registDate;


}
