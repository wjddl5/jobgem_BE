package com.sist.jobgem.dto;

import lombok.*;
import org.springframework.data.domain.Slice;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TalentResponseDto {
    private Slice<TalentDto> wishJobseekers;
}
