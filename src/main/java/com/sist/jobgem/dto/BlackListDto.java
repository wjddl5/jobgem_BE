package com.sist.jobgem.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlackListDto {
  private Integer id;
  private Integer coIdx;
  private Integer joIdx;
  private Integer usIdx;
  private String blTitle;
  private String blContent;
  private LocalDate blDate;
  private Integer blState;

}
