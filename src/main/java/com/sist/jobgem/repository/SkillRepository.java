package com.sist.jobgem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.jobgem.entity.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {

    @Modifying
    @Query("UPDATE Skill s SET s.skName = :editItemName WHERE s.id = :id")
    int updateSki(@Param("id") int id, @Param("editItemName") String editItemName);

    @Query("SELECT 1 FROM Skill s WHERE s.skName = :itemName")
    Integer findBySkName(@Param("itemName") String itemName);
}
