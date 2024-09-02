package com.sist.jobgem.dto;

import com.sist.jobgem.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
  private Integer id;
  private Integer boIdx;
  private Integer usIdx;
  private String commContent;
  private UserDto user;

  // Entity -> DTO 변환
  public static CommentDto fromEntity(Comment comment) {
    return new CommentDto(
        comment.getId(),
        comment.getBoIdx(),
        comment.getUsIdx(),
        comment.getCommContent(),
        UserDto.fromEntity(comment.getUser()));
  }

}