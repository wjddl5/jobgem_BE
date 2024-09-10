package com.sist.jobgem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.jobgem.dto.BoardDto;
import com.sist.jobgem.entity.Board;
import com.sist.jobgem.mapper.BoardMapper;
import com.sist.jobgem.repository.BoardRepository;

@Service
public class BoardService {

  @Autowired
  private BoardRepository boardRepository;

  // Board 엔티티를 BoardDto로 변환하는 메소드
  private BoardDto convertToDto(Board board) {
    return BoardDto.toDto(board);
  }

  // 게시판 리스트
  public Page<BoardDto> getBbsList(int boType, int boStatus, Pageable pageable, String searchType, String searchValue) {
    Page<Board> boardPage = null;

    Pageable pageable2 = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
        Sort.by(Sort.Direction.DESC, "id"));

    if (searchValue == null) {
      boardPage = boardRepository.findByBoTypeAndBoStatus(boType, boStatus, pageable2);
    } else {
      boardPage = boardRepository.findByWithSearch(boType, boStatus, pageable2, searchType, searchValue);
    }

    // Board -> BoardDto 변환
    List<BoardDto> boardDtoList = boardPage.getContent().stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());

    // 변환된 DtoList를 사용하여 새로운 Page<BoardDto> 객체를 생성
    return new PageImpl<>(boardDtoList, pageable2, boardPage.getTotalElements());
  }

  // =============

  // 게시글 상세보기
  public BoardDto getView(int id) {
    Board board = boardRepository.findById(id);
    BoardDto boardDto = convertToDto(board);
    return boardDto;
  }

  // 게시글 삭제
  @Transactional
  public boolean removeBbs(int id) {
    int chk = boardRepository.updateBoardStatus(id);
    if (chk == 1)
      return true;
    else
      return false;
  }

  // 게시글 저장
  public boolean writeBbs(int boType, int usIdx, String title, String content) {
    BoardDto bDto = new BoardDto();
    bDto.setBoType(boType);
    bDto.setUsIdx(usIdx);
    bDto.setBoTitle(title);
    bDto.setBoContent(content);
    bDto.setBoHit(0);
    bDto.setBoLike(0);
    bDto.setBoStatus(1);

    switch (boType) {
      case 1: // 공지사항
        bDto.setBoAnswer(null);
        break;
      case 2: // QnA
        bDto.setBoAnswer(0);
        break;
    }
    bDto.setBoWritedate(LocalDate.now());

    Board board = BoardMapper.INSTANCE.dtoToEntity(bDto);

    Board b = boardRepository.save(board);

    return b != null;
  }

  // 게시물 수정
  @Transactional
  public boolean editBbs(String boTitle, String boContent, int boId) {
    int chk = boardRepository.editBoard(boTitle, boContent, boId);
    if (chk == 1)
      return true;
    else
      return false;
  }

  // 게시글 답변상태 업데이트
  @Transactional
  public void updateAnswerStatusYes(int id) {
    boardRepository.updateAnswerStatusYes(id);
  }

  @Transactional
  public void updateAnswerStatusNo(int id) {
    boardRepository.updateAnswerStatusNo(id);
  }
}
