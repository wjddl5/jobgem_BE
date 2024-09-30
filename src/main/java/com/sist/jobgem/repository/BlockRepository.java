package com.sist.jobgem.repository;

import com.sist.jobgem.dto.BlockDto;
import com.sist.jobgem.dto.BlockResponseDto;
import com.sist.jobgem.entity.Block;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<Block, Integer>, BlockRepositoryCustom {

        @Query("SELECT new com.sist.jobgem.dto.BlockResponseDto(b) FROM Block b WHERE b.coIdx = :coIdx ORDER BY b.blDate DESC LIMIT 3")
        List<BlockResponseDto> findAllByCoIdx(@Param("coIdx") int coIdx);

        @Query("SELECT new com.sist.jobgem.dto.BlockDto(b) FROM Block b " +
                        "WHERE b.coIdx = :coIdx " +
                        "AND (:name IS NULL OR b.jobseeker.joName LIKE %:name%)")
        Page<BlockDto> findAllByCoIdxAndJoName(@Param("coIdx") int coIdx,
                        @Param("name") String name,
                        Pageable pageable);

        @Query("SELECT b FROM Block b JOIN Jobseeker j ON b.joIdx = j.id " +
                        "WHERE (:type = 'bldate' AND CAST(b.blDate as string) LIKE %:value%) " +
                        "OR (:type = 'blcontent' AND b.blContent LIKE %:value%) " +
                        "OR (:type = 'joinDate' AND CAST(j.user.usJoinDate as string) LIKE %:value%) " +
                        "OR (:type = 'leaveDate' AND CAST(j.user.usLeaveDate as string) LIKE %:value%) " +
                        "OR (:type = 'name' AND j.joName LIKE %:value%) " +
                        "OR (:type = 'phone' AND j.joTel LIKE %:value%) " +
                        "OR (:type = 'gender' AND j.joGender LIKE %:value%) " +
                        "OR (:type = 'birth' AND CAST(j.joBirth as string) LIKE %:value%) " +
                        "OR (:type = 'address' AND j.joAddress LIKE %:value%) " +
                        "OR (:type = 'edu' AND j.joEdu LIKE %:value%) " +
                        "OR (:type = 'sal' AND j.joSal LIKE %:value%)")
        Page<Block> findByTypeAndValuejobseekerContaining(@Param("type") String type, @Param("value") String value,
                        Pageable pageable);

        @Query("SELECT b FROM Block b JOIN Company c ON b.coIdx = c.id " +
                        "WHERE (:type = 'bldate' AND CAST(b.blDate as string) LIKE %:value%) OR " +
                        "(:type = 'blcontent' AND b.blContent LIKE %:value%) OR " +
                        "(:type = 'name' AND c.coName LIKE %:value%) OR " +
                        "(:type = 'number' AND c.coNumber LIKE %:value%) OR " +
                        "(:type = 'address' AND c.coAddress LIKE %:value%) OR " +
                        "(:type = 'tel' AND c.coTel LIKE %:value%) OR " +
                        "(:type = 'type' AND c.coType LIKE %:value%) OR " +
                        "(:type = 'open' AND CAST(c.coOpen as string) LIKE %:value%) OR " +
                        "(:type = 'employee' AND CAST(c.coEmployee as string) LIKE %:value%) OR " +
                        "(:type = 'sales' AND c.coSales LIKE %:value%) OR " +
                        "(:type = 'score' AND CAST(c.coScore as string) LIKE %:value%) OR " +
                        "(:type = 'managerName' AND c.coManagerName LIKE %:value%) OR " +
                        "(:type = 'managerTel' AND c.coManagerTel LIKE %:value%)")
        Page<Block> findByTypeAndValuecompanyContaining(@Param("type") String type, @Param("value") String value,
                        Pageable pageable);

        @Query("SELECT b FROM Block b " +
                        "join b.jobseeker j " +
                        "on b.jobseeker.id = j.id")
        Page<Block> blackjobseeker(Pageable pageable);

        @Query("SELECT b FROM Block b " +
                        "join b.company c " +
                        "on b.company.id = c.id")
        Page<Block> blackcompany(Pageable pageable);

        @Modifying
        @Query("DELETE FROM Block b WHERE b.id = :id")
        int deletecomjobBlock(@Param("id") int id);

}