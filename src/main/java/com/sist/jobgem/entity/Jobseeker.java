package com.sist.jobgem.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "jobseekers")
@Builder
public class Jobseeker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jo_idx", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "us_idx", nullable = false)
    private User user;

    @Column(name = "jo_name", nullable = false, length = 10)
    private String joName;

    @Column(name = "jo_birth")
    private LocalDate joBirth;

    @Column(name = "jo_address", length = 30)
    private String joAddress;

    @Column(name = "jo_tel", nullable = false, length = 15)
    private String joTel;

    @Column(name = "jo_gender", nullable = false, length = 1)
    private String joGender;

    @Column(name = "jo_img_url", length = 100)
    private String joImgUrl;

    @Column(name = "jo_edu", length = 10)
    private String joEdu;

    @Column(name = "jo_sal", length = 10)
    private String joSal;

}