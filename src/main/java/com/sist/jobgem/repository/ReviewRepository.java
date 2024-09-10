package com.sist.jobgem.repository;

import com.sist.jobgem.dto.ReviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.jobgem.entity.Review;

import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Page<Review> findByJoIdxAndReState(int idx, int reState, Pageable pageable);

    List<Review> findByCoIdxAndReState(int coIdx, int reState);

    int countByCoIdxAndReState(int coIdx, int reState);

    @Modifying
    @Transactional
    @Query("update Review r set r.reState = 0 where r.id = :id")
    int deleteReview(@Param("id") int id);

}
