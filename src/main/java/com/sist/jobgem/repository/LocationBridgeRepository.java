package com.sist.jobgem.repository;

import com.sist.jobgem.entity.LocationBridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationBridgeRepository extends JpaRepository<LocationBridge, Integer> {
    
}
