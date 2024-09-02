package com.sist.jobgem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "us_join_date", nullable = false)
    private Instant usJoinDate;

    @Column(name = "us_leave_date")
    private LocalDate usLeaveDate;

    @NotNull
    @Column(name = "us_type", nullable = false)
    private Integer usType;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "us_state", nullable = false)
    private Integer usState;

    @Size(max = 100)
    @Column(name = "refresh_token", length = 100)
    private String refreshToken;

}