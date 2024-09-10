package com.sist.jobgem.repository;

import com.sist.jobgem.entity.Applyment;
import com.sist.jobgem.entity.Review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplymentRepository extends JpaRepository<Applyment, Integer> {
    int countByPoIdx(int poIdx);

    Page<Applyment> findByJoIdxAndApState(int idx, int apState, Pageable pageable);
}
