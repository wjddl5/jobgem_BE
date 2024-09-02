package com.sist.jobgem.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co_idx", nullable = false)
    private Integer id;

    @Column(name = "us_idx", nullable = false)
    private Integer usIdx;

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
    private Integer coScore;

    @Column(name = "co_manager_name", nullable = false, length = 20)
    private String coManagerName;

    @Column(name = "co_manager_tel", nullable = false, length = 15)
    private String coManagerTel;

}