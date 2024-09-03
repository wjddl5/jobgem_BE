package com.sist.jobgem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.jobgem.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

  // List<Board> findByBoTypeAndBoStatus(int boType, int boStatus, Pageable
  // pageable);

  Page<Board> findByBoTypeAndBoStatus(int boType, int boStatus, Pageable pageable);

  @Query("SELECT b FROM Board b " +
      "WHERE b.boType = :boType " +
      "AND b.boStatus = :boStatus " +
      "AND ((:searchType = 'title' AND b.boTitle LIKE %:searchValue%) " +
      "OR (:searchType = 'content' AND b.boContent LIKE %:searchValue%)" +
      "OR (:searchType = 'writer' AND b.user.usId LIKE %:searchValue%))")
  Page<Board> findByWithSearch(@Param("boType") int boType, @Param("boStatus") int boStatus, Pageable pageable,
      @Param("searchType") String searchType, @Param("searchValue") String searchValue);

  Board findById(int id);

  @Modifying
  @Query("UPDATE Board b SET b.boStatus = 0 WHERE b.id = :id")
  int updateBoardStatus(@Param("id") int id);

}
