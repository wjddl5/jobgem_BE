package com.sist.jobgem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sist.jobgem.dto.BoardDto;
import com.sist.jobgem.dto.CommentDto;
import com.sist.jobgem.service.BoardService;
import com.sist.jobgem.service.CommentService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/bbs")
public class BoardController {

  @Autowired
  private BoardService boardService;

  @Autowired
  private CommentService commentService;

  // 공지사항 리스트
  @GetMapping("/notice")
  public ResponseEntity<Page<BoardDto>> getNoticeList(Pageable pageable,
      @RequestParam(value = "searchType", required = false) String searchType,
      @RequestParam(value = "searchValue", required = false) String searchValue) {
    return ResponseEntity.ok(boardService.getBbsList(1, 1, pageable, searchType, searchValue)); // boType 1 : 공지사항
  }

  // QnA 리스트
  @GetMapping("/qna")
  public ResponseEntity<Page<BoardDto>> getQnAList(Pageable pageable,
      @RequestParam(value = "searchType", required = false) String searchType,
      @RequestParam(value = "searchValue", required = false) String searchValue) {
    return ResponseEntity.ok(boardService.getBbsList(2, 1, pageable, searchType, searchValue)); // boType 2 : QnA
  }

  // My QnA 리스트
  @GetMapping("/qna/my")
  public ResponseEntity<Page<BoardDto>> getMyQnAList(Pageable pageable, @RequestParam(value = "usIdx") int usIdx) {
    return ResponseEntity.ok(boardService.getMyBbsList(2, 1, pageable, usIdx)); // boType 2 : QnA
  }

  // 게시글 상세보기
  @GetMapping("{id}")
  public ResponseEntity<Map<String, Object>> getView(@PathVariable int id) {
    Map<String, Object> map = new HashMap<>();
    BoardDto vo = boardService.getView(id);

    if (vo != null) {
      List<CommentDto> commentList = commentService.getCommList(id);
      map.put("vo", vo);
      map.put("commentList", commentList);
    }

    return ResponseEntity.ok(map);
  }

  // 게시글 작성
  @PostMapping("/write")
  public boolean addBbs(@RequestParam(value = "title") String title, @RequestParam(value = "content") String content,
      @RequestParam(value = "boType") int boType, @RequestParam(value = "usIdx") int usIdx) {
    return boardService.addBbs(boType, usIdx, title, content);
  }

  // 게시글 수정
  @PutMapping("/{id}")
  public boolean updateBbs(@PathVariable int id, @RequestParam(value = "title") String title,
      @RequestParam(value = "content") String content) {
    return boardService.updateBbs(title, content, id);
  }

  // 게시글 삭제
  @DeleteMapping("/{id}")
  public boolean deleteBbs(@PathVariable int id) {
    return boardService.deleteBbs(id);
  }

  @DeleteMapping("/removeList")
  public boolean deleteBbsList(@RequestParam(value = "chkList") List<String> chkList) {
    for (int i = 0; i < chkList.size(); i++) {
      Boolean chk = boardService.deleteBbs(Integer.parseInt(chkList.get(i)));
      if (!chk)
        return false;
    }
    return true;
  }

  @PutMapping("/answer/{id}/yes")
  public int updateAnswerStatusYes(@PathVariable int id) {
    return boardService.updateAnswerStatusYes(id);
  }

  @PutMapping("/answer/{id}/no")
  public int updateAnswerStatusNo(@PathVariable int id) {
    return boardService.updateAnswerStatusNo(id);
  }

  @PutMapping("/hit/{id}")
  public void hitUp(@PathVariable int id) {
    boardService.hitUp(id);
  }

}
