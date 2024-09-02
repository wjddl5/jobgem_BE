package com.sist.jobgem.repository;

import com.sist.jobgem.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertsRepository extends JpaRepository<Alert, Integer> {
    
}
