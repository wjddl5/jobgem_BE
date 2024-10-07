package com.sist.jobgem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRequestDto {
    private Integer usIdx;
    private Integer cmIdx;
    private String chContent;
    private Integer chIsRead;
    private String chDate;
}
