package com.sist.jobgem.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.sist.jobgem.entity.Jobseeker;
import com.sist.jobgem.entity.Offer;
import com.sist.jobgem.entity.Skill;
import com.sist.jobgem.entity.User;

import jakarta.validation.constraints.Pattern;
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

    private String joName;

    private String joTel;

    private LocalDate joBirth;

    private Integer joAge;

    private String joAddress;

    private String joGender;

    private String joImgurl;

    private String joEdu;

    private String joSal;

    private List<Skill> skills;

    private List<Offer> offers;

    private List<Integer> skillIds; // 스킬 ID 목록 추가

    public JobseekerDto(Jobseeker jobseeker) {
        this.id = jobseeker.getId();
        this.user = jobseeker.getUser();
        this.joName = jobseeker.getJoName();
        this.joTel = jobseeker.getJoTel();
        this.joBirth = jobseeker.getJoBirth();
        this.joAddress = jobseeker.getJoAddress();
        this.joGender = jobseeker.getJoGender();
        this.joEdu = jobseeker.getJoEdu();
        this.joSal = jobseeker.getJoSal();
        this.joImgurl = jobseeker.getJoImgUrl();
        this.skills = jobseeker.getSkills();
        this.skillIds = jobseeker.getSkills().stream()
                .map(Skill::getId)
                .collect(Collectors.toList()); // Skill ID 목록 추출
        this.joAge = LocalDate.now().getYear() - joBirth.getYear();
    }

}
