package com.sist.jobgem.repository;

import com.sist.jobgem.dto.BlockDto;
import com.sist.jobgem.entity.Block;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<Block, Integer> {

    @Query("SELECT new com.sist.jobgem.dto.BlockDto(b) FROM Block b WHERE b.coIdx = :coIdx ORDER BY b.blDate DESC LIMIT 3")
    List<BlockDto> findAllByCoIdx(@Param("coIdx") int coIdx);

    @Query("SELECT new com.sist.jobgem.dto.BlockDto(b) FROM Block b " +
            "WHERE b.coIdx = :coIdx " +
            "AND (:name IS NULL OR b.jobseeker.joName LIKE %:name%)")
    Page<BlockDto> findAllByCoIdxAndJoName(@Param("coIdx") int coIdx,
                                         @Param("name") String name,
                                         Pageable pageable);

    
    @Query("SELECT b FROM Block b " +
           "WHERE (:type = 'bldate' AND cast(b.blDate as string) LIKE %:value%) " +
           "OR (:type = 'blcontent' AND b.blContent LIKE %:value%) " +
           "OR (:type = 'joinDate' AND cast(b.jobseeker.user.usJoinDate as string) LIKE %:value%) " +
           "OR (:type = 'leaveDate' AND cast(b.jobseeker.user.usLeaveDate as string) LIKE %:value%) " +
           "OR (:type = 'name' AND b.jobseeker.joName LIKE %:value%) " +
           "OR (:type = 'phone' AND b.jobseeker.joTel LIKE %:value%) " +
           "OR (:type = 'gender' AND b.jobseeker.joGender LIKE %:value%) " +
           "OR (:type = 'birth' AND cast(b.jobseeker.joBirth as string) LIKE %:value%) " +
           "OR (:type = 'address' AND b.jobseeker.joAddress LIKE %:value%) " +
           "OR (:type = 'edu' AND b.jobseeker.joEdu LIKE %:value%) " +
           "OR (:type = 'sal' AND b.jobseeker.joSal LIKE %:value%)")
    Page<Block> findByTypeAndValueContaining(@Param("type") String type, @Param("value") String value, Pageable pageable);
}