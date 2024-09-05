package com.sist.jobgem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.sist.jobgem.entity.WorkSchedules;

@Repository
public interface WorkSchedulesRepository extends JpaRepository<WorkSchedules, Integer> {
    List<WorkSchedules> findByPoIdx(Integer poIdx);
}
