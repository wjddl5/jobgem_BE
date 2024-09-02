package com.sist.jobgem.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JobseekerDto {

    private Integer id;

    private Integer usIdx;

    private Integer hcIdx;

    private String joName;

    private LocalDate joBirth;

    private String joAddress;

    private String joGender;

    private String joImgurl;

    private String joEdu;

    private String joSal;

    private Integer joState;

}
