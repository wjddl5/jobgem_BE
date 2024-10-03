package com.sist.jobgem.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlertRequestDto {
    private Integer usIdx;
    private String alContent;
    private Instant alDate;
    private Integer alIsRead;
    private Integer alState;
}
