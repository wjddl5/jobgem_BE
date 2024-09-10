package com.sist.jobgem.dto;

import com.sist.jobgem.entity.Applyment;
import com.sist.jobgem.entity.Post;
import com.sist.jobgem.entity.Review;

import lombok.*;

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

    public static ApplymentDto fromEntity(Applyment applyment) {
        return ApplymentDto.builder()
                .id(applyment.getId())
                .poIdx(applyment.getJoIdx())
                .joIdx(applyment.getJoIdx())
                .reIdx(applyment.getReIdx())
                .apDate(applyment.getApDate())
                .apPass(applyment.getApPass())
                .apRead(applyment.getApRead())
                .apState(applyment.getApState())
                .post(applyment.getPost())
                .build();
    }
}
