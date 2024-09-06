package com.sist.jobgem.dto;

import lombok.*;

import java.time.LocalDate;

import com.sist.jobgem.entity.Jobseeker;

@Getter
@Setter
public class BlockDto {
    private String name;
    private LocalDate blDate;
    private String blContent;
    private Jobseeker jobseeker;
    
    public BlockDto(String name, LocalDate blDate, String blContent) {
        this.name = name;
        this.blDate = blDate;
        this.blContent = blContent;
    }
}
