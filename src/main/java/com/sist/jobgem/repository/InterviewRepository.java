package com.sist.jobgem.repository;

import com.sist.jobgem.dto.InterviewDto;
import com.sist.jobgem.entity.Interview;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Integer> {
    Page<Interview> findByJoIdxAndInState(int idx, int inState, Pageable pageable);

    int countByCoIdxAndInState(int coIdx, int inState);

    @Query("select new com.sist.jobgem.dto.InterviewDto(r) from Interview r where r.coIdx = :coIdx and r.inState = 1")
    Page<InterviewDto> findByCoIdx(@Param("coIdx") int coIdx, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update Interview r set r.inState = 0 where r.id = :id")
    int deleteInterview(@Param("id") int id);
}
