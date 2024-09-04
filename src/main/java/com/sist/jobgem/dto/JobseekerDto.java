package com.sist.jobgem.dto;

import java.time.LocalDate;

import com.sist.jobgem.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobseekerDto {

    private Integer id;

    private User user;

    private Integer hcIdx;

    private String joName;

    private String joTel;

    private LocalDate joBirth;

    private String joAddress;

    private String joGender;

    private String joImgurl;

    private String joEdu;

    private String joSal;

    private Integer joState;

}
