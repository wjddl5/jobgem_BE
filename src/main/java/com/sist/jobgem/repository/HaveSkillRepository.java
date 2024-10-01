package com.sist.jobgem.repository;

import com.sist.jobgem.dto.HaveSkillDto;
import com.sist.jobgem.entity.HaveSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HaveSkillRepository extends JpaRepository<HaveSkill, Integer> {
    @Query("SELECT new com.sist.jobgem.dto.HaveSkillDto(hs.skill) " +
            "FROM HaveSkill hs " +
            "WHERE hs.jobseeker.id = :jobseekerId")
    List<HaveSkillDto> findSkillsByJobseekerId(@Param("jobseekerId") int id);

    @Modifying
    @Query("DELETE FROM HaveSkill s WHERE s.skIdx = :id")
    void deleteBySkIdx(@Param("id") int id);
}
