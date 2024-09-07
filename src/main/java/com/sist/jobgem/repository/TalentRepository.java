package com.sist.jobgem.repository;

import com.sist.jobgem.dto.TalentDto;
import com.sist.jobgem.entity.Talent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TalentRepository extends JpaRepository<Talent, Integer> {
    int countByCoIdx(int coIdx);

    @Query("SELECT new com.sist.jobgem.dto.TalentDto(t) FROM Talent t WHERE t.coIdx = :coIdx")
    Slice<TalentDto> findByCoIdx(int coIdx, Pageable pageable);
}
