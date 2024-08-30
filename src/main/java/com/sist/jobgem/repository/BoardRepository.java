package com.sist.jobgem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.sist.jobgem.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
