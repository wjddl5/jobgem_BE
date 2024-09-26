package com.sist.jobgem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.jobgem.dto.CommentDto;
import com.sist.jobgem.entity.Comment;
import com.sist.jobgem.mapper.CommentMapper;
import com.sist.jobgem.repository.CommentRepository;

@Service
public class CommentService {

  @Autowired
  private CommentRepository commentRepository;

  // Comment 엔티티를 CommentDto로 변환하는 메소드
  private CommentDto convertToDto(Comment comment) {
    return CommentDto.toDto(comment);
  }

  // 댓글 리스트
  public List<CommentDto> getCommList(Integer boIdx) {
    List<Comment> commentList = commentRepository.findByBoIdxAndCommStatus(boIdx, 1);
    List<CommentDto> commentDtoList = commentList.stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());
    return commentDtoList;
  }

  // 댓글 삭제
  @Transactional
  public boolean deleteComment(int id) {
    int chk = commentRepository.updateCommentStatus(id);
    if (chk == 1)
      return true;
    else
      return false;
  }

  // 댓글 입력
  public boolean addComment(int boIdx, int usIdx, String content) {
    CommentDto cDto = new CommentDto();
    cDto.setBoIdx(boIdx);
    cDto.setUsIdx(usIdx);
    cDto.setCommContent(content);
    cDto.setCommStatus(1);

    Comment comment = CommentMapper.INSTANCE.dtoToEntity(cDto);
    Comment comm = commentRepository.save(comment);
    return comm != null;
  }

  // 댓글 수정
  @Transactional
  public boolean updateComment(int id, String content) {
    int chk = commentRepository.editComment(id, content);
    if (chk == 1)
      return true;
    else
      return false;
  }

}
