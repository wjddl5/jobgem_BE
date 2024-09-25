package com.sist.jobgem.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScrapDto {
    private Integer id;
    private Integer poIdx;
    private Integer joIdx;
    private LocalDate scDate; 
    private Integer scState;
}
