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
  private UserDto user;
  private List<CommentDto> commentList;

  // Entity -> DTO 변환
  public static BoardDto fromEntity(Board board) {
    return new BoardDto(
        board.getId(),
        board.getUsIdx(),
        board.getBoType(),
        board.getBoTitle(),
        board.getBoContent(),
        board.getBoWritedate(),
        board.getBoHit(),
        board.getBoLike(),
        board.getBoAnswer(),
        board.getBoStatus(),
        UserDto.fromEntity(board.getUser()),
        board.getCommentList().stream()
            .map(CommentDto::fromEntity)
            .collect(Collectors.toList()));
  }
}
