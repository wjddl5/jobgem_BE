package com.sist.jobgem.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRequestDto {
    private Integer usIdx;
    private Integer cmIdx;
    private String chContent;
    private String chDate = LocalDate.now().toString();
}
