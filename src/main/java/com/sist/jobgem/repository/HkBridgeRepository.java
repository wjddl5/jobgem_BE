package com.sist.jobgem.repository;

import com.sist.jobgem.entity.HkBridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HkBridgeRepository extends JpaRepository<HkBridge, Integer> {

  @Modifying
  @Query("DELETE FROM HkBridge h WHERE h.hkIdx = :id")
  int deleteByHkIdx(@Param("id") int id);
}
