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

        @Modifying
        @Query("DELETE FROM Block b WHERE b.id = :id")
        int deletecomjobBlock(@Param("id") int id);

        @Query("SELECT b FROM Block b WHERE b.id = :id")
        Block findById(@Param("id") int id);
}