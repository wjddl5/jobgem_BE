package com.sist.jobgem.dto;

import java.time.LocalDate;

import com.sist.jobgem.entity.Company;
import com.sist.jobgem.entity.Review;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReviewDto {

    private Integer id;

    private Integer joIdx;

    private Integer coIdx;

    private String reTitle;

    private String reContent;

    private Integer reScore;

    private LocalDate reWriteDate;

    private Integer reState;

    private String coName;

    public static ReviewDto fromEntity(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .joIdx(review.getJoIdx())
                .coIdx(review.getCoIdx())
                .reTitle(review.getReTitle())
                .reContent(review.getReContent())
                .reScore(review.getReScore())
                .reWriteDate(review.getReWriteDate())
                .reState(review.getReState())
                .coName(review.getCompany().getCoName())
                .build();
    }
}
