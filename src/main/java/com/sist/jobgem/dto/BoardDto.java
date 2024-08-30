package com.sist.jobgem.dto;

import java.time.LocalDate;
import java.util.List;

import com.sist.jobgem.entity.Board;
import com.sist.jobgem.entity.Comment;
import com.sist.jobgem.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {

  public BoardDto(Board board) {
    this.id = board.getId();
    this.usIdx = board.getUsIdx();
    this.boType = board.getBoType();
    this.boTitle = board.getBoTitle();
    this.boContent = board.getBoContent();
    this.boWritedate = board.getBoWritedate();
    this.boHit = board.getBoHit();
    this.boLike = board.getBoLike();
    this.boAnswer = board.getBoAnswer();
    this.boStatus = board.getBoStatus();
    this.user = board.getUser();
    this.commentList = board.getCommentList();
  }

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

  private User user;

  private List<Comment> commentList;

}
