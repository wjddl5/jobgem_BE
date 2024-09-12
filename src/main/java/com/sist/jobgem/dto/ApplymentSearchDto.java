package com.sist.jobgem.dto;

import java.time.LocalDate;

import com.sist.jobgem.entity.Applyment;
import com.sist.jobgem.entity.Interview;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplymentSearchDto {
    private Integer joIdx;
    private Integer apRead;
    private Integer poIdx;
    LocalDate startDate;
    LocalDate endDate;

}
