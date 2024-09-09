package com.sist.jobgem.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CompanyIndexDto {
    private CompanyDto company;
    private Integer postCount;
    private Integer noPostCount;
    private Integer reviewCount;
    private Integer interviewCount;
    private Integer talentCount;
    private Integer fitJobseekerCount;
    private List<BlockDto> blockList;

    private CompanyIndexDto(CompanyDto company, int postCount, int noPostCount, int reviewCount, int interviewCount, int talentCount, int fitJobseekerCount, List<BlockDto> blockList) {
        this.company = company;
        this.postCount = postCount;
        this.noPostCount = noPostCount;
        this.reviewCount = reviewCount;
        this.interviewCount = interviewCount;
        this.talentCount = talentCount;
        this.fitJobseekerCount = fitJobseekerCount;
        this.blockList = blockList;
    }

    public CompanyIndexDto() {}
}
