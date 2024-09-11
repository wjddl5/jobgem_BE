package com.sist.jobgem.dto;

import java.time.LocalDate;
import com.sist.jobgem.entity.Blacklist;
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
  private int blProcess;
  private Integer blState;
  private String usId;
  private String coName;
  private String joName;

  public static BlackListDto toDto(Blacklist blacklist) {
    BlackListDto.BlackListDtoBuilder bDto = BlackListDto.builder();

    if (blacklist.getCompany() != null) {
      String coName = blacklist.getCompany().getCoName();
      if (coName != null)
        bDto.coName(coName);
      bDto.coIdx(blacklist.getCoIdx());
    }
    if (blacklist.getJobseeker() != null) {
      String joName = blacklist.getJobseeker().getJoName();
      if (joName != null)
        bDto.joName(joName);
      bDto.joIdx(blacklist.getJoIdx());
    }
    bDto
        .id(blacklist.getId())
        .blTitle(blacklist.getBlTitle())
        .blContent(blacklist.getBlContent())
        .blDate(blacklist.getBlDate())
        .blProcess(blacklist.getBlProcess())
        .usId(blacklist.getUser().getUsId())
        .build();

    return bDto.build();
  }

}
