package com.sist.jobgem.repository;

import com.sist.jobgem.entity.LocationDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDoRepository extends JpaRepository<LocationDo, Integer> {
    
}
