package com.sist.jobgem.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlertResponseDto {
    private Integer id;
    private Integer usIdx;
    private String alContent;
    private Instant alDate;
    private Integer alIsRead;
    private Integer alState;
}
