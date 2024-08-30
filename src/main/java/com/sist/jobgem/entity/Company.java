package com.sist.jobgem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "companies")
public class Company {
    @Id
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
    @Column(name = "co_type", length = 4)
    private String coType;

    @NotNull
    @Column(name = "co_employee", nullable = false)
    private Integer coEmployee;

    @Size(max = 20)
    @NotNull
    @Column(name = "co_imgUrl", nullable = false, length = 20)
    private String coImgurl;

    @Size(max = 20)
    @NotNull
    @Column(name = "co_thumbimgUrl", nullable = false, length = 20)
    private String coThumbimgurl;

    @Size(max = 20)
    @NotNull
    @Column(name = "co_sales", nullable = false, length = 20)
    private String coSales;

    @Column(name = "co_score")
    private Integer coScore;

    @Column(name = "co_state")
    private Integer coState;

}