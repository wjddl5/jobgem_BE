package com.sist.jobgem.dto;

import com.sist.jobgem.entity.Applyment;
import com.sist.jobgem.entity.Post;

import lombok.*;
import com.sist.jobgem.entity.Jobseeker;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplymentDto {
    private Integer id;
    private Integer poIdx;
    private Integer joIdx;
    private Integer reIdx;
    private LocalDate apDate;
    private Integer apPass;
    private Integer apRead;
    private Integer apState;
    private Post post;
    private Jobseeker jobseeker;

    public static ApplymentDto fromEntity(Applyment applyment) {
        return ApplymentDto.builder()
                .id(applyment.getId())
                .poIdx(applyment.getJoIdx())
                .joIdx(applyment.getJoIdx())
                .reIdx(applyment.getReIdx())
                .apDate(applyment.getApDate())
                .apRead(applyment.getApRead())
                .post(applyment.getPost())
                .jobseeker(applyment.getJobseeker())
                .build();
    }

    // 필드 기반 생성자 추가
    public ApplymentDto(Applyment applyment) {
        this.id = applyment.getId();
        this.post = applyment.getPost();
        this.joIdx = applyment.getJoIdx();
        this.reIdx = applyment.getReIdx();
        this.apRead = applyment.getApRead();
        this.apDate = applyment.getApDate();
    }

    public ApplymentDto(ApplymentDto applymentDto) {
        this.id = applymentDto.getId();
        this.poIdx = applymentDto.getPoIdx();
        this.joIdx = applymentDto.getJoIdx();
        this.reIdx = applymentDto.getReIdx();
        this.apState = applymentDto.getApState();
        this.apRead = applymentDto.getApRead();
        this.apDate = applymentDto.getApDate();
    }
}
