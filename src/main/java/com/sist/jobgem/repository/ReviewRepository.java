package com.sist.jobgem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sist.jobgem.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Page<Review> findByJoIdx(int idx, Pageable pageable);

    int countByCoIdxAndReState(int coIdx, int reState);
}
