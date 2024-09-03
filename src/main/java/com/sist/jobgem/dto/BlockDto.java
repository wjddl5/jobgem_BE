package com.sist.jobgem.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class BlockDto {
    private String name;
    private LocalDate blDate;
    private String blContent;

    public BlockDto(String name, LocalDate blDate, String blContent) {
        this.name = name;
        this.blDate = blDate;
        this.blContent = blContent;
    }
}
