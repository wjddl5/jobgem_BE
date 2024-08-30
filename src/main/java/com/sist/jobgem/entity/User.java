package com.sist.jobgem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "us_idx", nullable = false)
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Column(name = "us_id", nullable = false, length = 20)
    private String usId;

    @Size(max = 100)
    @NotNull
    @Column(name = "us_pw", nullable = false, length = 100)
    private String usPw;

    @NotNull
    @Column(name = "join_date", nullable = false)
    private Instant joinDate;

    @Column(name = "leave_date")
    private LocalDate leaveDate;

    @NotNull
    @Column(name = "us_type", nullable = false)
    private Integer usType;

    @NotNull
    @Column(name = "us_state", nullable = false)
    private Integer usState;

    @Size(max = 50)
    @Column(name = "access_token", length = 50)
    private String accessToken;

    @Size(max = 50)
    @NotNull
    @Column(name = "refresh_token", nullable = false, length = 50)
    private String refreshToken;

}