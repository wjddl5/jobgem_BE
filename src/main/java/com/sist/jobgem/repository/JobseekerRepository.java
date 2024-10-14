package com.sist.jobgem.repository;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.jobgem.dto.JobseekerDto;
import com.sist.jobgem.entity.Jobseeker;

@Repository
public interface JobseekerRepository extends JpaRepository<Jobseeker, Integer>, JobseekerRepositoryCustom {
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
                        "    HAVING COUNT(DISTINCT h.skill.id) >= 3" +
                        ") AND j.user.usState = 1" +
                        "AND j.id NOT IN (" +
                        "    SELECT t.joIdx " +
                        "    FROM Talent t " +
                        "    WHERE t.coIdx = :companyId" +
                        ") AND j.id NOT IN(" +
                        "    SELECT o.joIdx " +
                        "    FROM Offer o " +
                        "    WHERE o.coIdx = :companyId" +
                        "    AND o.ofState = 1" +
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
                        "    HAVING COUNT(DISTINCT h.skill.id) >= 3" +
                        ") AND j.user.usState = 1" +
                        "AND j.id NOT IN (" +
                        "    SELECT t.joIdx " +
                        "    FROM Talent t " +
                        "    WHERE t.coIdx = :companyId" +
                        ") AND j.id NOT IN(" +
                        "    SELECT o.joIdx " +
                        "    FROM Offer o " +
                        "    WHERE o.coIdx = :companyId" +
                        "    AND o.ofState = 1" +
                        ")")
        Slice<JobseekerDto> findByWithfitJobseeker(@Param("companyId") int id, Pageable pageable);

        Optional<Jobseeker> findByUser_Id(int id);

        Optional<Jobseeker> findByJoNameAndJoTel(String joName, String joTel);
}
