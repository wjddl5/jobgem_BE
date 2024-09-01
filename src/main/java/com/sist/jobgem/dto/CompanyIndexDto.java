package com.sist.jobgem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyIndexDto {

    private CompanyDto company;
    private Integer postCount;
    private Integer noPostCount;

}
