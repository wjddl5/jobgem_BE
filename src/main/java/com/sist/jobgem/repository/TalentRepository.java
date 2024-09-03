package com.sist.jobgem.repository;

import com.sist.jobgem.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TalentRepository extends JpaRepository<Talent, Integer> {
    int countByCoIdx(int coIdx);
}
