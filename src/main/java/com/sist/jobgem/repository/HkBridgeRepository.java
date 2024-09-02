package com.sist.jobgem.repository;

import com.sist.jobgem.entity.HkBridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HkBridgeRepository extends JpaRepository<HkBridge, Integer> {
    
}
