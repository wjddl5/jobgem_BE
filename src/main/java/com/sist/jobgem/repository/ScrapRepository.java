package com.sist.jobgem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.jobgem.entity.Scrap;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, Integer> {

    // joIdx로 scState가 1인 Scrap 개수를 세는 쿼리 메서드
    @Query("SELECT COUNT(s) FROM Scrap s WHERE s.joIdx = :joIdx AND s.scState = 1")
    int countByJoIdxAndScStateIsOne(@Param("joIdx") Integer joIdx);

    Scrap findByJoIdxAndPoIdx(Integer joIdx, Integer poIdx);

    List<Scrap> findByJoIdx(Integer joIdx);
}
