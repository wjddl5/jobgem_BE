package com.sist.jobgem.repository;

import com.sist.jobgem.entity.CareersBridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CareersBridgeRepository extends JpaRepository<CareersBridge, Integer> {

  @Modifying
  @Query("DELETE FROM CareersBridge c WHERE c.crIdx = :id")
  int deleteByCrIdx(@Param("id") int id);
}
