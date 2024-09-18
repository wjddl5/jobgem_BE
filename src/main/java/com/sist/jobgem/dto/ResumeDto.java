package com.sist.jobgem.dto;

import com.sist.jobgem.entity.Post;
import com.sist.jobgem.entity.Resume;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDto {
    private Integer id;

    private Integer joIdx;

    private String reTitle;

    private String reContent;

    private String reFileUrl;

    private LocalDate reWriteDate;

    private Integer reDefault;

    private Integer reState;

    public ResumeDto(ResumeDto resumeDto) {
        this.id = resumeDto.getId();
        this.joIdx = resumeDto.getJoIdx();
        this.reTitle = resumeDto.getReTitle();
        this.reContent = resumeDto.getReContent();
        this.reFileUrl = resumeDto.getReFileUrl();
        this.reWriteDate = resumeDto.getReWriteDate();
        this.reDefault = resumeDto.getReDefault();
        this.reState = resumeDto.getReState();

    }

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