package com.sist.jobgem.dto;

import com.sist.jobgem.entity.Block;
import com.sist.jobgem.entity.Company;
import com.sist.jobgem.entity.Jobseeker;

import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlockDto {
    private Integer id;
    private String name;
    private Integer joIdx;
    private Integer coIdx;
    private LocalDate blDate;
    private String blContent;
    private Jobseeker jobseeker;
    private Company company;
    private String joBirth;
    private String joAddress;
    private String joTel;
    private String joGender;
    private String joImgUrl;
    private String joEdu;
    private String joSal;
    private Integer joAge;
    private Instant joinDate;
    private LocalDate leaveDate;

    public BlockDto(Block entity) {
        this.id = entity.getId();
        this.name = entity.getJobseeker().getJoName();
        this.blDate = entity.getBlDate();
        this.blContent = entity.getBlContent();
    }

    public BlockDto(Block entity, Jobseeker jobseeker) {
        this.id = entity.getId();
        this.blDate = entity.getBlDate();
        this.blContent = entity.getBlContent();
        this.name = jobseeker.getJoName();
        if (jobseeker.getJoBirth() != null) {
            this.joBirth = jobseeker.getJoBirth().toString();
        }
        if (jobseeker.getJoBirth() != null) {
            this.joAge = LocalDate.now().getYear() - jobseeker.getJoBirth().getYear();
        } else {
            this.joAge = null;
        }
        this.joAddress = jobseeker.getJoAddress();
        this.joTel = jobseeker.getJoTel();
        this.joGender = jobseeker.getJoGender();
        this.joImgUrl = jobseeker.getJoImgUrl();
        this.joEdu = jobseeker.getJoEdu();
        this.joSal = jobseeker.getJoSal();
        this.joinDate = jobseeker.getUser().getUsJoinDate();
        this.leaveDate = jobseeker.getUser().getUsLeaveDate();
    }

}
