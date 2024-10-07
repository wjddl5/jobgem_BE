package com.sist.jobgem.repository;

import java.util.List;

import com.sist.jobgem.entity.InterestCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestCompanyRepository extends JpaRepository<InterestCompany, Integer> {
    // joIdx로 개수를 세는 쿼리 메서드
    @Query("SELECT COUNT(i) FROM InterestCompany i WHERE i.joIdx = :joIdx")
    int countByJoIdx(@Param("joIdx") Integer joIdx);

    @Query("SELECT i FROM InterestCompany i WHERE i.coIdx = :coIdx AND i.joIdx = :joIdx")
    InterestCompany findByCoIdxAndJoIdx(@Param("coIdx") Integer coIdx, @Param("joIdx") Integer joIdx);

    List<InterestCompany> findByJoIdx(Integer joIdx);
}
