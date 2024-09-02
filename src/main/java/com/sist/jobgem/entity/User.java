package com.sist.jobgem.entity;

import java.time.Instant;
import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "us_idx", nullable = false)
    private Integer id;

    @Column(name = "us_id", nullable = false, length = 20)
    private String usId;

    @Column(name = "us_pw", nullable = false, length = 100)
    private String usPw;

    @Column(name = "us_join_date", nullable = false)
    private Instant usJoinDate;

    @Column(name = "us_leave_date")
    private LocalDate usLeaveDate;

    @Column(name = "us_type", nullable = false)
    private Integer usType;

    @ColumnDefault("1")
    @Column(name = "us_state", nullable = false)
    private Integer usState;

    @Column(name = "refresh_token", length = 100)
    private String refreshToken;

}