package com.sist.jobgem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.CommentDto;
import com.sist.jobgem.repository.CommentRepository;

@Service
public class CommentService {

  @Autowired
  private CommentRepository commentRepository;

  public List<CommentDto> getCommList(Integer boId) {
    return commentRepository.findByBoIdx(boId);
  }
}
