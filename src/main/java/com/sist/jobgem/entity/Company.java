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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "companies")
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co_idx", nullable = false)
    private Integer id;

    @Column(name = "us_idx", nullable = false, insertable = false, updatable = false)
    private Integer usIdx;

    @OneToOne
    @JoinColumn(name = "us_idx", nullable = false)
    private User user;

    @Column(name = "co_name", nullable = false, length = 20)
    private String coName;

    @Column(name = "co_number", nullable = false, length = 15)
    private String coNumber;

    @Column(name = "co_address", nullable = false, length = 30)
    private String coAddress;

    @Column(name = "co_tel", nullable = false, length = 15)
    private String coTel;

    @Column(name = "co_type", nullable = false, length = 4)
    private String coType;

    @Column(name = "co_open")
    private LocalDate coOpen;

    @Column(name = "co_employee")
    private Integer coEmployee;

    @Column(name = "co_img_url", length = 100)
    private String coImgUrl;

    @Column(name = "co_thumbimg_url", length = 100)
    private String coThumbimgUrl;

    @Column(name = "co_sales", length = 20)
    private String coSales;

    @Column(name = "co_score")
    private Double coScore;

    @Column(name = "co_manager_name", nullable = false, length = 20)
    private String coManagerName;

    @Column(name = "co_manager_tel", nullable = false, length = 15)
    private String coManagerTel;

}