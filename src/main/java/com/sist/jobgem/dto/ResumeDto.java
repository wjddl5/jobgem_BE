package com.sist.jobgem.dto;

import com.sist.jobgem.entity.Resume;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResumeDto {
    private Integer id;

    private Integer joIdx;

    private String reTitle;

    private String reContent;

    private String reFileUrl;

    private LocalDate reWriteDate;

    private Integer reDefault;

    private Integer reState;

    public static ResumeDto fromEntity(Resume resume) {
        return ResumeDto.builder()
                .id(resume.getId())
                .joIdx(resume.getJoIdx())
                .reTitle(resume.getReTitle())
                .reContent(resume.getReContent())
                .reFileUrl(resume.getReFileUrl())
                .reWriteDate(resume.getReWriteDate())
                .reDefault(resume.getReDefault())
                .reState(resume.getReState())
                .build();
    }
}