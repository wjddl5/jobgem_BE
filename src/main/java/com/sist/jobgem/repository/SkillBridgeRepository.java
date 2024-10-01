package com.sist.jobgem.repository;

import com.sist.jobgem.entity.SkillBridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillBridgeRepository extends JpaRepository<SkillBridge, Integer> {

  @Modifying
  @Query("DELETE FROM SkillBridge s WHERE s.skIdx = :id")
  void deleteBySkIdx(@Param("id") int id);
}
