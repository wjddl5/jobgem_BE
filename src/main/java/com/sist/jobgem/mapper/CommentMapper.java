package com.sist.jobgem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sist.jobgem.dto.CommentDto;
import com.sist.jobgem.entity.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {
  CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

  Comment dtoToEntity(CommentDto cDto);

  List<CommentDto> toDtoList(List<Comment> comment);
}
