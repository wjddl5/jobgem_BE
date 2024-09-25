package com.sist.jobgem.dto;

import java.time.LocalDate;

import com.sist.jobgem.entity.InterestCompany;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class InterestCompanyDto {
    private Integer id;
    private Integer joIdx;
    private Integer coIdx;
    private LocalDate icDate;

    public InterestCompanyDto(InterestCompany interestCompany){
        this.id = interestCompany.getId();
        this.joIdx = interestCompany.getJoIdx();
        this.coIdx = interestCompany.getCoIdx();
        this.icDate = interestCompany.getIcDate();
    }
}
