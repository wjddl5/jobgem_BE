package com.sist.jobgem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.jobgem.entity.Skill;
import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {

    @Modifying
    @Query("UPDATE Skill s SET s.skName = :editItemName WHERE s.id = :id")
    int editSki(@Param("id") int id, @Param("editItemName") String editItemName);
    List<Skill> findByIdIn(List<Integer> skIdx);
}
