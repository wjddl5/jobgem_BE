package com.sist.jobgem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.BoardDto;
import com.sist.jobgem.entity.Board;
import com.sist.jobgem.mapper.BoardMapper;
import com.sist.jobgem.repository.BoardRepository;

@Service
public class BoardService {

  @Autowired
  private BoardRepository boardRepository;

  public List<BoardDto> getAll() {
    List<Board> list = boardRepository.findAll(); // status + bo_type 조건으로 변경
    BoardDto[] ar = null;

    List<BoardDto> dto_list = BoardMapper.INSTANCE.toDtoList(list);

    // if (!dto_list.isEmpty()) {
    // ar = new BoardDto[dto_list.size()];
    // dto_list.toArray(ar);
    // }
    return dto_list;
  }

  public BoardDto getView(int id) {
    Board board = boardRepository.findById(id).get();
    BoardDto boardDto = BoardMapper.INSTANCE.toDto(board);
    return boardDto;
  }

}
