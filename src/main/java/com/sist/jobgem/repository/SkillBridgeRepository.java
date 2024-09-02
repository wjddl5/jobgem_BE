package com.sist.jobgem.repository;

import com.sist.jobgem.entity.SkillBridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillBridgeRepository extends JpaRepository<SkillBridge, Integer> {
    
}
