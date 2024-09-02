package com.sist.jobgem.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.sist.jobgem.entity.Board;
import com.sist.jobgem.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
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
  private String usId;

  public static BoardDto toDto(Board board) {
    return BoardDto.builder()
        .boTitle(board.getBoTitle())
        .boContent(board.getBoContent())
        .boWritedate(board.getBoWritedate())
        .boHit(board.getBoHit())
        .boLike(board.getBoLike())
        .boAnswer(board.getBoAnswer())
        .usId(board.getUser().getUsId())
        .build();
  }
}
