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

  // @RequestMapping("/qna/list")
  // public Map<String, Object> getQnaList(Pageable pageable) {
  // Map<String, Object> map = new HashMap<>();
  // List<BoardDto> ar = boardService.getBbsList(2, 1, pageable); // boType 2 :
  // Q&A

  // map.put("ar", ar);
  // map.put("length", ar.size());

  // return map;
  // }

  @RequestMapping(value = { "/notice/view", "/qna/view" })
  public Map<String, Object> getView(@RequestParam(value = "id") int id) {
    Map<String, Object> map = new HashMap<>();
    BoardDto vo = boardService.getView(id);
    List<CommentDto> commentList = commentService.getCommList(id);

    map.put("vo", vo);
    map.put("commentList", commentList);

    return map;
  }

  @RequestMapping("/notice/remove")
  public boolean removeBbs(@RequestParam(value = "id") int id) {
    return boardService.removeBbs(id);
  }

  @RequestMapping("/notice/removeList")
  public boolean removeBbsList(@RequestParam(value = "chkList") List<String> chkList) {
    for (int i = 0; i < chkList.size(); i++) {
      boardService.removeBbs(Integer.parseInt(chkList.get(i)));
    }
    return true;
  }
}
