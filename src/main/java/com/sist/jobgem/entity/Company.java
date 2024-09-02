package com.sist.jobgem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "us_idx", nullable = false)
    private Integer usIdx;

    @Size(max = 20)
    @NotNull
    @Column(name = "co_name", nullable = false, length = 20)
    private String coName;

    @Size(max = 15)
    @NotNull
    @Column(name = "co_number", nullable = false, length = 15)
    private String coNumber;

    @Size(max = 30)
    @NotNull
    @Column(name = "co_address", nullable = false, length = 30)
    private String coAddress;

    @Size(max = 15)
    @NotNull
    @Column(name = "co_tel", nullable = false, length = 15)
    private String coTel;

    @Size(max = 4)
    @NotNull
    @Column(name = "co_type", nullable = false, length = 4)
    private String coType;

    @Column(name = "co_open")
    private LocalDate coOpen;

    @Column(name = "co_employee")
    private Integer coEmployee;

    @Size(max = 100)
    @Column(name = "co_img_url", length = 100)
    private String coImgUrl;

    @Size(max = 100)
    @Column(name = "co_thumbimg_url", length = 100)
    private String coThumbimgUrl;

    @Size(max = 20)
    @Column(name = "co_sales", length = 20)
    private String coSales;

    @Column(name = "co_score")
    private Integer coScore;

    @Size(max = 20)
    @NotNull
    @Column(name = "co_manager_name", nullable = false, length = 20)
    private String coManagerName;

    @Size(max = 15)
    @NotNull
    @Column(name = "co_manager_tel", nullable = false, length = 15)
    private String coManagerTel;

}