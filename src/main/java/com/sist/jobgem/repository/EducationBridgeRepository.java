package com.sist.jobgem.repository;

import com.sist.jobgem.entity.EducationBridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationBridgeRepository extends JpaRepository<EducationBridge, Integer> {

  @Modifying
  @Query("DELETE FROM EducationBridge e WHERE e.edIdx = :id")
  void deleteByEdIdx(@Param("id") int id);

}
