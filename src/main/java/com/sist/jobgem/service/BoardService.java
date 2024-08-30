package com.sist.jobgem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.BoardDto;
import com.sist.jobgem.entity.Board;
import com.sist.jobgem.repository.BoardRepository;

@Service
public class BoardService {

  @Autowired
  private BoardRepository boardRepository;

  public BoardDto[] getAll() {
    List<Board> list = boardRepository.findAll(); // status 조건으로 변경
    List<BoardDto> dto_list = new ArrayList<>();
    BoardDto[] ar = null;

    for (Board a : list) {
      BoardDto bDto = new BoardDto(a);
      dto_list.add(bDto);
    }

    if (!dto_list.isEmpty()) {
      ar = new BoardDto[dto_list.size()];
      dto_list.toArray(ar);
    }

    return ar;
  }

  public BoardDto getView(int id) {
    Board board = boardRepository.findById(id).get();
    BoardDto boardDto = new BoardDto(board);

    return boardDto;
  }

}
