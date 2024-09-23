package com.sist.jobgem.dto;

import java.time.LocalDate;

import com.sist.jobgem.entity.Interview;
import com.sist.jobgem.entity.Jobseeker;
import com.sist.jobgem.entity.Review;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewDto {

    private Integer id;

    private Integer joIdx;

    private Integer coIdx;

    private String inContent;

    private LocalDate inWriteDate;

    private Integer inLevel;

    private Integer inState;

    private String coName;

    private Jobseeker jobseeker;

    public static InterviewDto fromEntity(Interview interview) {
        return InterviewDto.builder()
                .id(interview.getId())
                .joIdx(interview.getJoIdx())
                .coIdx(interview.getCoIdx())
                .inContent(interview.getInContent())
                .inWriteDate(interview.getInWriteDate())
                .inLevel(interview.getInLevel())
                .coName(interview.getCompany().getCoName())
                .build();
    }

    public InterviewDto(Interview interview) {
        this.id = interview.getId();
        this.joIdx = interview.getJoIdx();
        this.coIdx = interview.getCoIdx();
        this.inContent = interview.getInContent();
        this.inWriteDate = interview.getInWriteDate();
        this.coName = interview.getCompany().getCoName();
        this.inLevel = interview.getInLevel();
        this.jobseeker = interview.getJobseeker();
        this.inState = interview.getInState();
    }

    public InterviewDto(InterviewDto interviewDto) {
        this.id = interviewDto.getId();
        this.joIdx = interviewDto.getJoIdx();
        this.coIdx = interviewDto.getCoIdx();
        this.inContent = interviewDto.getInContent();
        this.inWriteDate = interviewDto.getInWriteDate();
        this.inLevel = interviewDto.getInLevel();
        this.jobseeker = interviewDto.getJobseeker();
        this.inState = interviewDto.getInState();
    }
}
