package com.sist.jobgem.dto;

import com.sist.jobgem.entity.Board;
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
  private Integer commStatus;
  private UserDto userDto;
  private BoardDto boardDto;
}