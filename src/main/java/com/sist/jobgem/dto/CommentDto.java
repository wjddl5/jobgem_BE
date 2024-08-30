package com.sist.jobgem.dto;

import com.sist.jobgem.entity.Comment;
import com.sist.jobgem.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

  public CommentDto(Comment comment) {
    this.id = comment.getId();
    this.boIdx = comment.getBoIdx();
    this.usIdx = comment.getUsIdx();
    this.commContent = comment.getCommContent();
    this.user = comment.getUser();
  }

  private Integer id;

  private Integer boIdx;

  private Integer usIdx;

  private String commContent;

  private User user;
}
