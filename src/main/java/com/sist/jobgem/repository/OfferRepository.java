package com.sist.jobgem.repository;

import com.sist.jobgem.entity.Offer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
    Page<Offer> findByJoIdxAndOfState(int idx, int ofState, Pageable pageable);
}
