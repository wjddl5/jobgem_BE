package com.sist.jobgem.dto;

import com.sist.jobgem.entity.Company;
import com.sist.jobgem.entity.Jobseeker;
import com.sist.jobgem.entity.Talent;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TalentDto {
    private Integer id;
    private Integer joIdx;
    private Integer coIdx;
    private LocalDate taDate;
    private Company company;
    private Jobseeker jobseeker;

    private TalentDto(Talent talent) {
        this.id = talent.getId();
        this.joIdx = talent.getJoIdx();
        this.coIdx = talent.getCoIdx();
        this.taDate = talent.getTaDate();
        this.company = talent.getCompany();
        this.jobseeker = talent.getJobseeker();
    }
}
