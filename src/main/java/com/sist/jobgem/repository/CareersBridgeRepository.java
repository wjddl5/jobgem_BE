package com.sist.jobgem.repository;

import com.sist.jobgem.entity.CareersBridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareersBridgeRepository extends JpaRepository<CareersBridge, Integer> {
    
}
