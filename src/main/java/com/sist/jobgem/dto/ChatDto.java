package com.sist.jobgem.dto;

import com.sist.jobgem.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatDto {
    private Integer id;
    private Integer usIdx;
    private Integer cmIdx;
    private String chContent;
    private LocalDateTime chDate;
    private Integer chIsRead;

    private User user;
}
