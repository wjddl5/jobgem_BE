package com.sist.jobgem.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

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
  private String boImage;
  private LocalDate boWritedate;
  private Integer boHit;
  private Integer boLike;
  private Integer boAnswer;
  private Integer boStatus;
  private String usId;
  private int commCount;

  public static BoardDto toDto(Board board) {
    List<Comment> list = board.getCommList();
    List<Comment> list2 = new ArrayList<Comment>();

    for (Comment cvo : list) {
      if (cvo.getCommStatus() == 1) {
        list2.add(cvo);
      }
    }
    return BoardDto.builder()
        .id(board.getId())
        .boTitle(board.getBoTitle())
        .boContent(board.getBoContent())
        .boWritedate(board.getBoWritedate())
        .boImage(board.getBoImage())
        .boHit(board.getBoHit())
        .boLike(board.getBoLike())
        .boAnswer(board.getBoAnswer())
        .usId(board.getUser().getUsId())
        .usIdx(board.getUser().getId())
        .commCount(list2.size())
        .build();
  }
}
