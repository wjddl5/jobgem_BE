package com.sist.jobgem.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyIndexDto {
    private CompanyDto company;
    private Integer postCount;
    private Integer noPostCount;
    private Integer reviewCount;
    private Integer interviewCount;
    private Integer talentCount;
    private Integer fitJobseekerCount;
    private List<BlockResponseDto> blockList;
    private List<ChatroomResponseDto> chatList;
}
