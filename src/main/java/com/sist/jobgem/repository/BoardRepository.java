package com.sist.jobgem.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.sist.jobgem.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

  // List<Board> findByBoTypeAndBoStatus(int boType, int boStatus, Pageable
  // pageable);
  Page<Board> findByBoTypeAndBoStatus(int boType, int boStatus, Pageable pageable);

}
