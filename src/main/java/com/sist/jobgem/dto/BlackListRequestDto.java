package com.sist.jobgem.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlackListRequestDto {
    private Integer coIdx;
    private Integer joIdx;
    private Integer usIdx;
    private String blTitle;
    private String blContent;
    private LocalDate blDate;
    private int blProcess;
    private Integer blState;
}
