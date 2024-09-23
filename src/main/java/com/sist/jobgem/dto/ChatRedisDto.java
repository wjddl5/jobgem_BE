package com.sist.jobgem.dto;

import com.sist.jobgem.entity.User;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRedisDto {
    private Integer usIdx;
    private Integer cmIdx;
    private String chContent;
    private String chDate;
    private Integer chIsRead;
}
