package com.sist.jobgem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "jobseekers")
public class Jobseeker {
    @Id
    @Column(name = "jo_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "us_idx", nullable = false)
    private Integer usIdx;

    @NotNull
    @Column(name = "hc_idx", nullable = false)
    private Integer hcIdx;

    @Size(max = 10)
    @NotNull
    @Column(name = "jo_name", nullable = false, length = 10)
    private String joName;

    @NotNull
    @Column(name = "jo_birth", nullable = false)
    private LocalDate joBirth;

    @Size(max = 30)
    @NotNull
    @Column(name = "jo_address", nullable = false, length = 30)
    private String joAddress;

    @Size(max = 1)
    @NotNull
    @Column(name = "jo_gender", nullable = false, length = 1)
    private String joGender;

    @Size(max = 30)
    @NotNull
    @Column(name = "jo_imgUrl", nullable = false, length = 30)
    private String joImgurl;

    @Size(max = 10)
    @NotNull
    @Column(name = "jo_edu", nullable = false, length = 10)
    private String joEdu;

    @Size(max = 10)
    @Column(name = "jo_sal", length = 10)
    private String joSal;

    @NotNull
    @Column(name = "jo_state", nullable = false)
    private Integer joState;

}