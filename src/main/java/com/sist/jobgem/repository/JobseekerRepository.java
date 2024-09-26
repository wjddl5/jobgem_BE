package com.sist.jobgem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.jobgem.dto.JobseekerDto;
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
                        "    HAVING COUNT(DISTINCT h.skill.id) >= 5" +
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

        @Query("SELECT j " +
                        "FROM Jobseeker j JOIN User u ON j.user.id = u.id " +
                        "WHERE (:type = 'leaveDate' AND CAST(u.usLeaveDate AS string) LIKE %:value%) OR " +
                        "(:type = 'joinDate' AND CAST(u.usJoinDate AS string) LIKE %:value%) OR " +
                        "(:type = 'name' AND j.joName LIKE %:value%) OR " +
                        "(:type = 'tel' AND j.joTel LIKE %:value%) OR " +
                        "(:type = 'address' AND j.joAddress LIKE %:value%) OR " +
                        "(:type = 'gender' AND j.joGender LIKE %:value%) OR " +
                        "(:type = 'edu' AND j.joEdu LIKE %:value%) OR " +
                        "(:type = 'sal' AND j.joSal LIKE %:value%) OR " +
                        "(:type = 'birth' AND CAST(j.joBirth AS string) LIKE %:value%)")
        Page<Jobseeker> findByTypeAndValueContaining(@Param("type") String type, @Param("value") String value,
                        Pageable pageable);

        @Query("SELECT j FROM Jobseeker j LEFT JOIN Block b ON j.id = b.joIdx WHERE b.joIdx IS NULL " +
                        "AND ((:type IS NULL AND :value IS NULL) OR " +
                        "(:type = 'name' AND j.joName LIKE %:value%) OR " +
                        "(:type = 'birth' AND CAST(j.joBirth AS string) LIKE %:value%) OR " +
                        "(:type = 'tel' AND j.joTel LIKE %:value%) OR " +
                        "(:type = 'address' AND j.joAddress LIKE %:value%) OR " +
                        "(:type = 'edu' AND j.joEdu LIKE %:value%) OR " +
                        "(:type = 'sal' AND j.joSal LIKE %:value%) OR " +
                        "(:type = 'gender' AND j.joGender LIKE %:value%) OR " +
                        "(:type = 'joinDate' AND CAST(j.user.usJoinDate AS string) LIKE %:value%) OR " +
                        "(:type = 'leaveDate' AND CAST(j.user.usLeaveDate AS string) LIKE %:value%))")
        List<Jobseeker> findJobseekersNotInBlock(@Param("type") String type, @Param("value") String value);

        @Query("SELECT j FROM Jobseeker j LEFT JOIN Block b ON j.id = b.joIdx WHERE b.joIdx IS NULL")
        List<Jobseeker> findAllJobseekersNotInBlock();

        Optional<Jobseeker> findByUser_Id(int id);
}
