package com.sist.jobgem.repository;

import com.sist.jobgem.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Integer> {
    List<Alert> findAllByUsIdx(int usIdx);
}
