package com.sist.jobgem.repository;

import com.sist.jobgem.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Integer> {
    int countByCoIdxAndInState(int coIdx, int inState);
}
