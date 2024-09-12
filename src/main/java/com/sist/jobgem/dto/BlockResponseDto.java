package com.sist.jobgem.dto;

import com.sist.jobgem.entity.Block;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlockResponseDto {
    private Integer id;
    private String name;
    private LocalDate blDate;
    private String blContent;

    public BlockResponseDto(Block entity) {
        this.id = entity.getId();
        this.name = entity.getJobseeker().getJoName();
        this.blDate = entity.getBlDate();
        this.blContent = entity.getBlContent();
    }
}
