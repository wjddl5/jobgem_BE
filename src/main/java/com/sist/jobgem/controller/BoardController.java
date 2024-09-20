package com.sist.jobgem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
  public Page<BoardDto> getNoticeList(Pageable pageable,
      @RequestParam(value = "searchType", required = false) String searchType,
      @RequestParam(value = "searchValue", required = false) String searchValue) {
    return boardService.getBbsList(1, 1, pageable, searchType, searchValue); // boType 1 : 공지사항
  }

  // QnA 리스트
  @GetMapping("/qna")
  public Page<BoardDto> getQnAList(Pageable pageable,
      @RequestParam(value = "searchType", required = false) String searchType,
      @RequestParam(value = "searchValue", required = false) String searchValue) {
    return boardService.getBbsList(2, 1, pageable, searchType, searchValue); // boType 2 : QnA
  }

  // My QnA 리스트
  @GetMapping("/qna/my")
  public Page<BoardDto> getMyQnAList(Pageable pageable, @RequestParam(value = "usIdx") int usIdx) {
    return boardService.getMyBbsList(2, 1, pageable, usIdx); // boType 2 : QnA
  }

  // 게시글 상세보기
  @GetMapping("{id}")
  public Map<String, Object> getView(@PathVariable int id) {
    Map<String, Object> map = new HashMap<>();
    BoardDto vo = boardService.getView(id);
    List<CommentDto> commentList = commentService.getCommList(id);

    map.put("vo", vo);
    map.put("commentList", commentList);

    return map;
  }

  // 게시글 작성
  @PostMapping("/write")
  public boolean writeBbs(@RequestParam(value = "title") String title, @RequestParam(value = "content") String content,
      @RequestParam(value = "boType") int boType, @RequestParam(value = "usIdx") int usIdx) {
    return boardService.writeBbs(boType, usIdx, title, content);
  }

  // 게시글 수정
  @PutMapping("/{id}")
  public boolean editBbs(@PathVariable int id, @RequestParam(value = "title") String title,
      @RequestParam(value = "content") String content) {
    return boardService.editBbs(title, content, id);
  }

  // 게시글 삭제
  @DeleteMapping("/{id}")
  public boolean removeBbs(@PathVariable int id) {
    return boardService.removeBbs(id);
  }

  @DeleteMapping("/removeList")
  public boolean removeBbsList(@RequestParam(value = "chkList") List<String> chkList) {
    for (int i = 0; i < chkList.size(); i++) {
      Boolean chk = boardService.removeBbs(Integer.parseInt(chkList.get(i)));
      if (!chk)
        return false;
    }
    return true;
  }

  @PutMapping("/answer/{id}/yes")
  public void updateAnswerStatusYes(@PathVariable int id) {
    boardService.updateAnswerStatusYes(id);
  }

  @PutMapping("/answer/{id}/no")
  public void updateAnswerStatusNo(@PathVariable int id) {
    boardService.updateAnswerStatusNo(id);
  }

  @PutMapping("/hit/{id}")
  public void hitUp(@PathVariable int id) {
    boardService.hitUp(id);
  }

}
