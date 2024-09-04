package com.sist.jobgem.dto;

import java.time.LocalDate;

import com.sist.jobgem.entity.Interview;
import com.sist.jobgem.entity.Review;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InterviewDto {

    private Integer id;

    private Integer joIdx;

    private Integer coIdx;

    private String inContent;

    private LocalDate inWriteDate;

    private Integer inState;

    private String coName;

    public static InterviewDto fromEntity(Interview interview) {
        return InterviewDto.builder()
                .id(interview.getId())
                .joIdx(interview.getJoIdx())
                .coIdx(interview.getCoIdx())
                .inContent(interview.getInContent())
                .inWriteDate(interview.getInWriteDate())
                .coName(interview.getCompany().getCoName())
                .build();
    }
}
