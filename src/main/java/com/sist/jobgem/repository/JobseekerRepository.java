package com.sist.jobgem.repository;

import com.sist.jobgem.dto.JobseekerDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.jobgem.entity.Jobseeker;

@Repository
public interface JobseekerRepository extends JpaRepository<Jobseeker, Integer> {
    @Query("SELECT COUNT(j) " +
            "FROM Jobseeker j " +
            "WHERE j.id IN (" +
            "    SELECT h.jobseeker.id " +
            "    FROM HaveSkill h " +
            "    JOIN h.skill s " +
            "    JOIN SkillBridge sb ON s.id = sb.skill.id " +
            "    JOIN Post p ON sb.post.id = p.id " +
            "    JOIN p.company c " +
            "    WHERE c.id = :companyId " +
            "    GROUP BY h.jobseeker.id " +
            "    HAVING COUNT(DISTINCT h.skill.id) >= 5" +
            ")")
    int countByWithfitJobseeker(@Param("companyId") int id);

    @Query("SELECT new com.sist.jobgem.dto.JobseekerDto(j) " +
            "FROM Jobseeker j " +
            "WHERE j.id IN (" +
            "    SELECT h.jobseeker.id " +
            "    FROM HaveSkill h " +
            "    JOIN h.skill s " +
            "    JOIN SkillBridge sb ON s.id = sb.skill.id " +
            "    JOIN Post p ON sb.post.id = p.id " +
            "    JOIN p.company c " +
            "    WHERE c.id = :companyId " +
            "    GROUP BY h.jobseeker.id " +
            "    HAVING COUNT(DISTINCT h.skill.id) >= 5" +
            ")")
    Slice<JobseekerDto> findByWithfitJobseeker(@Param("companyId") int id, Pageable pageable);
}
