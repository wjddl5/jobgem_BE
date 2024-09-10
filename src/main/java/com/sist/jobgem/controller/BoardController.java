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

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/api/bbs")
public class BoardController {

  @Autowired
  private BoardService boardService;

  @Autowired
  private CommentService commentService;

  @RequestMapping("/notice/list")
  public Page<BoardDto> getNoticeList(Pageable pageable,
      @RequestParam(value = "searchType", required = false) String searchType,
      @RequestParam(value = "searchValue", required = false) String searchValue) {
    return boardService.getBbsList(1, 1, pageable, searchType, searchValue); // boType 1 : 공지사항
  }

  @RequestMapping(value = { "/notice/view", "/qna/view" })
  public Map<String, Object> getView(@RequestParam(value = "id") int id) {
    Map<String, Object> map = new HashMap<>();
    BoardDto vo = boardService.getView(id);
    List<CommentDto> commentList = commentService.getCommList(id);

    map.put("vo", vo);
    map.put("commentList", commentList);

    return map;
  }

  @RequestMapping("/remove")
  public boolean removeBbs(@RequestParam(value = "id") int id) {
    return boardService.removeBbs(id);
  }

  @RequestMapping("/removeList")
  public boolean removeBbsList(@RequestParam(value = "chkList") List<String> chkList) {
    for (int i = 0; i < chkList.size(); i++) {
      Boolean chk = boardService.removeBbs(Integer.parseInt(chkList.get(i)));
      if (!chk)
        return false;
    }
    return true;
  }

  @RequestMapping("/notice/write")
  public boolean writeBbs(@RequestParam(value = "title") String title, @RequestParam(value = "content") String content,
      @RequestParam(value = "boType") int boType, @RequestParam(value = "usIdx") int usIdx) {
    return boardService.writeBbs(boType, usIdx, title, content);
  }

  @RequestMapping("/notice/edit")
  public boolean editBbs(@RequestParam(value = "title") String title, @RequestParam(value = "content") String content,
      @RequestParam(value = "boId") int boId) {
    return boardService.editBbs(title, content, boId);
  }

  @RequestMapping("/qna/list")
  public Page<BoardDto> getQnAList(Pageable pageable,
      @RequestParam(value = "searchType", required = false) String searchType,
      @RequestParam(value = "searchValue", required = false) String searchValue) {
    return boardService.getBbsList(2, 1, pageable, searchType, searchValue); // boType 2 : QnA
  }

  @RequestMapping("/answerYes")
  public void updateAnswerStatusYes(@RequestParam(value = "id") int id) {
    boardService.updateAnswerStatusYes(id);
  }

  @RequestMapping("/answerNo")
  public void updateAnswerStatusNo(@RequestParam(value = "id") int id) {
    boardService.updateAnswerStatusNo(id);
  }

}
