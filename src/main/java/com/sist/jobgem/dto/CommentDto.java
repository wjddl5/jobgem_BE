package com.sist.jobgem.dto;

import com.sist.jobgem.entity.Board;
import com.sist.jobgem.entity.Comment;
import com.sist.jobgem.entity.Jobseeker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
  private Integer id;
  private Integer boIdx;
  private Integer usIdx;
  private String commContent;
  private Integer commStatus;
  private String usId;
  private int usType;
  private BoardDto boardDto;

  public static CommentDto toDto(Comment comment) {
    return CommentDto.builder()
        .id(comment.getId())
        .commContent(comment.getCommContent())
        .usId(comment.getUser().getUsId())
        .usIdx(comment.getUser().getId())
        .usType(comment.getUser().getUsType())
        .build();
  }

}