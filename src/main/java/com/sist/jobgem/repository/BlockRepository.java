package com.sist.jobgem.repository;

import com.sist.jobgem.dto.BlockDto;
import com.sist.jobgem.entity.Block;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BlockRepository extends JpaRepository<Block, Integer> {
    @Query("SELECT new com.sist.jobgem.dto.BlockDto(b.jobseeker.joName, b.blDate, b.blContent) FROM Block b WHERE b.coIdx = :coIdx")
    Page<BlockDto> findAllByCoIdx(@Param("coIdx") int coIdx, Pageable pageable);
}