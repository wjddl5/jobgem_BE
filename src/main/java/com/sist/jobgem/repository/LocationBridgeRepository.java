package com.sist.jobgem.repository;

import com.sist.jobgem.entity.LocationBridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationBridgeRepository extends JpaRepository<LocationBridge, Integer> {

  @Modifying
  @Query("DELETE FROM LocationBridge l WHERE l.lgIdx = :id")
  int deleteByLgIdx(@Param("id") int id);
}
