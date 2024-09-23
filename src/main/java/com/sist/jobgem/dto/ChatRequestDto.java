package com.sist.jobgem.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRequestDto {
    private Integer usIdx;
    private Integer cmIdx;
    private String chContent;
    private Integer chIsRead = 0;
    private String chDate = LocalDateTime.now().toString();
}
