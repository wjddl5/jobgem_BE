package com.sist.jobgem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.sist.jobgem.entity.WorkDay;

@Repository
public interface WorkDayRepository extends JpaRepository<WorkDay, Integer> {
    List<WorkDay> findByIdIn(List<Integer> wdIdx);
}
