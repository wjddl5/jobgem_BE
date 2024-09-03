package com.sist.jobgem.repository;

import com.sist.jobgem.entity.HaveSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HaveSkillRepository extends JpaRepository<HaveSkill, Integer> {
    
}
