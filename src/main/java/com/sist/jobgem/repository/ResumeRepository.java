package com.sist.jobgem.repository;

import com.sist.jobgem.entity.Resume;

import jakarta.transaction.Transactional;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Integer> {
    Page<Resume> findByJoIdxAndReState(int idx, int reState, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update Resume r set r.reState = 0 where r.id = :id")
    int deleteResume(@Param("id") int id);

    // 특정 유저(joIdx)의 re_default가 1인 이력서를 찾는 쿼리
    Optional<Resume> findByJoIdxAndReDefault(int joIdx, int reDefault);

    @Modifying
    @Transactional
    @Query("UPDATE Resume r SET r.reDefault = 0 WHERE r.joIdx = :joIdx")
    void resetDefaultResume(@Param("joIdx") int joIdx);

    @Modifying
    @Transactional
    @Query("UPDATE Resume r SET r.reDefault = 1 WHERE r.id = :resumeId")
    void setDefaultResume(@Param("resumeId") int resumeId);
}
