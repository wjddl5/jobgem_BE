package com.sist.jobgem.dto;

import com.sist.jobgem.entity.Resume;
import com.sist.jobgem.entity.Review;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResumeListDto {
    private Integer id;

    private Integer joIdx;

    private String reTitle;

    private LocalDate reWriteDate;

    private Integer reState;

    public static ResumeListDto fromEntity(Resume resume) {
        return ResumeListDto.builder()
                .id(resume.getId())
                .joIdx(resume.getJoIdx())
                .reTitle(resume.getReTitle())
                .reWriteDate(resume.getReWriteDate())
                .reState(resume.getReState())
                .build();
    }
}