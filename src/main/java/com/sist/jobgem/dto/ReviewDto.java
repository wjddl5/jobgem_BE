package com.sist.jobgem.dto;

import java.time.LocalDate;

import com.sist.jobgem.entity.Company;
import com.sist.jobgem.entity.Jobseeker;
import com.sist.jobgem.entity.Review;

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

    private Company company;

    private Jobseeker jobseeker;

    public ReviewDto(ReviewDto reviewDto) {
        this.id = reviewDto.getId();
        this.coIdx = reviewDto.getCoIdx();
        this.joIdx = reviewDto.getJoIdx();
        this.reTitle = reviewDto.getReTitle();
        this.reContent = reviewDto.getReContent();
        this.reScore = reviewDto.getReScore();
        this.reWriteDate = reviewDto.getReWriteDate();
        this.reState = reviewDto.getReState();
        this.company = reviewDto.getCompany();
    }

    private ReviewDto(Review review){
        this.id = review.getId();
        this.coIdx = review.getCoIdx();
        this.joIdx = review.getJoIdx();
        this.reTitle = review.getReTitle();
        this.reContent = review.getReContent();
        this.reScore = review.getReScore();
        this.reWriteDate = review.getReWriteDate();
        this.reState = review.getReState();
        this.company = review.getCompany();
    }

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
