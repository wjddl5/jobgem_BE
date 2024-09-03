package com.sist.jobgem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.jobgem.dto.CommentDto;
import com.sist.jobgem.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @RequestMapping("/remove")
  public boolean removeComment(@RequestParam(value = "id") int id) {
    return commentService.removeComment(id);
  }

  @RequestMapping("/write")
  public boolean writeComment(@RequestParam(value = "boIdx") int boIdx, @RequestParam(value = "usIdx") int usIdx,
      @RequestParam(value = "content") String content) {
    return commentService.writeComment(boIdx, usIdx, content);
  }

}
