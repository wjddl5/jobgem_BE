package com.sist.jobgem.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.sist.jobgem.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
  private Integer id;
  private Integer usIdx;
  private Integer boType;
  private String boTitle;
  private String boContent;
  private LocalDate boWritedate;
  private Integer boHit;
  private Integer boLike;
  private Integer boAnswer;
  private Integer boStatus;

  public static BoardDto fromEntity(Board board) {
    return new BoardDto();
  }
}
