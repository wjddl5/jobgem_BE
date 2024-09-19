package com.sist.jobgem.dto;

import lombok.*;

import java.time.LocalDate;

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
    private LocalDate blDate = LocalDate.now();
    private int blProcess = 0;
    private Integer blState = 1;
}
