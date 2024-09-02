package com.sist.jobgem.repository;

import com.sist.jobgem.entity.Applyment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplymentRepository extends JpaRepository<Applyment, Integer> {
    
}
