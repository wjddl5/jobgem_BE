package com.sist.jobgem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sist.jobgem.entity.Skill;
import java.util.List;
@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
    List<Skill> findByIdIn(List<Integer> skIdx);
}
