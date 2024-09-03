package com.sist.jobgem.repository;

import com.sist.jobgem.entity.LocationGuSi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationGuSiRepository extends JpaRepository<LocationGuSi, Integer> {
    
}
