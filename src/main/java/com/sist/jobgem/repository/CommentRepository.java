package com.sist.jobgem.repository;

import com.sist.jobgem.dto.CommentDto;
import com.sist.jobgem.entity.Comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
  List<CommentDto> findByBoIdx(Integer boId);

}
