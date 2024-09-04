package com.sist.jobgem.repository;

import com.sist.jobgem.entity.Comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
  List<Comment> findByBoIdxAndCommStatus(Integer boIdx, Integer commStatus);

  @Modifying
  @Query("UPDATE Comment c SET c.commStatus = 0 WHERE c.id = :id")
  int updateCommentStatus(@Param("id") int id);

  @Modifying
  @Query("UPDATE Comment c SET c.commContent = :content WHERE c.id = :id")
  int editComment(@Param("id") int id, @Param("content") String content);

}
