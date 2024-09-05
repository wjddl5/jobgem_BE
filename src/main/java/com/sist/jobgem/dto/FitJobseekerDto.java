package com.sist.jobgem.dto;

import lombok.*;
import org.springframework.data.domain.Slice;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FitJobseekerDto {
    private Slice<JobseekerDto> fitJobseekers;
}
