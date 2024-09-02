package com.sist.jobgem.repository;

import com.sist.jobgem.entity.EducationBridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationBridgeRepository extends JpaRepository<EducationBridge, Integer> {
    
}
