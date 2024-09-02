package com.sist.jobgem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import com.sist.jobgem.dto.BoardDto;
import com.sist.jobgem.service.BoardService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class BoardController {

  @Autowired
  private BoardService boardService;

  @RequestMapping("/bbs/notice/list")
  public Page<BoardDto> getNoticeList(Pageable pageable, String searchType, String searchValue) {
    return boardService.getBbsList(1, 1, pageable, searchType, searchValue); // boType 1 : 공지사항

  }

  // @RequestMapping("/bbs/qna/list")
  // public Map<String, Object> getQnaList(Pageable pageable) {
  // Map<String, Object> map = new HashMap<>();
  // List<BoardDto> ar = boardService.getBbsList(2, 1, pageable); // boType 2 :
  // Q&A

  // map.put("ar", ar);
  // map.put("length", ar.size());

  // return map;
  // }

  @RequestMapping(value = { "/bbs/notice/view", "/bbs/qna/view" })
  public Map<String, Object> getView(@RequestParam(value = "id") int id) {
    System.out.println("aaaaaaaaaaaaaaaa");
    Map<String, Object> map = new HashMap<>();
    BoardDto vo = boardService.getView(id);

    map.put("vo", vo);

    return map;
  }

}
