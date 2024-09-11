package com.sist.jobgem.dto;

import com.sist.jobgem.entity.Block;
import com.sist.jobgem.entity.Company;
import com.sist.jobgem.entity.Jobseeker;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlockDto {
    private Integer id;
    private String name;
    private Integer joIdx;
    private Integer coIdx;
    private LocalDate blDate;
    private String blContent;
    private Jobseeker jobseeker;
    private Company company;
    
    public BlockDto(Block entity) {
        this.id = entity.getId();
        this.name = entity.getJobseeker().getJoName();
        this.blDate = entity.getBlDate();
        this.blContent = entity.getBlContent();
    }
}
