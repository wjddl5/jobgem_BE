package com.sist.jobgem.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sist.jobgem.dto.BoardDto;
import com.sist.jobgem.service.BoardService;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class BoardController {

  @Autowired
  private BoardService boardService;

  @RequestMapping("/bbs/notice/list")
  public Map<String, Object> getList() {
    Map<String, Object> map = new HashMap<>();
    BoardDto[] ar = boardService.getAll();

    map.put("ar", ar);
    map.put("length", ar.length);

    return map;
  }

  @RequestMapping("/bbs/notice/view")
  public Map<String, Object> getView(int id) {
    Map<String, Object> map = new HashMap<>();
    BoardDto vo = boardService.getView(id);

    map.put("vo", vo);

    return map;
  }

}
