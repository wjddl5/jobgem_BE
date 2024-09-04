package com.sist.jobgem.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.sist.jobgem.dto.BoardDto;
import com.sist.jobgem.entity.Board;

@Mapper
public interface BoardMapper {
  BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

  BoardDto toDto(Board board);

  Board dtoToEntity(BoardDto boradDto);

  List<BoardDto> toDtoList(List<Board> b_list);

}
