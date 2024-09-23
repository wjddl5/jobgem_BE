package com.sist.jobgem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.jobgem.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @PostMapping("/write")
  public boolean writeComment(@RequestParam(value = "boIdx") int boIdx, @RequestParam(value = "usIdx") int usIdx,
      @RequestParam(value = "content") String content) {
    return commentService.writeComment(boIdx, usIdx, content);
  }

  @PutMapping("/{id}")
  public boolean editComment(@PathVariable int id, @RequestParam(value = "content") String content) {
    return commentService.editComment(id, content);
  }

  @DeleteMapping("/{id}")
  public boolean removeComment(@PathVariable int id) {
    return commentService.removeComment(id);
  }

}
