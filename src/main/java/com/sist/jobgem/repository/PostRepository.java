package com.sist.jobgem.repository;

import com.sist.jobgem.dto.PostCountApplyDto;
import com.sist.jobgem.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    int countByCoIdxAndPoState(int coIdx, int poState);
    
    @Query("SELECT new com.sist.jobgem.dto.PostCountApplyDto(p.id, p.coIdx, p.poTitle, p.poContent, p.poDate, p.poDeadline, p.poImgurl, p.poSal, p.poWorkhour, p.poSubType, p.poAddr, p.poEmail, p.poFax, p.poState, CAST(COUNT(a) AS INTEGER) as applyCount) " +
            "FROM Post p LEFT JOIN Applyment a ON p.id = a.poIdx " +
            "WHERE p.coIdx = :coIdx " +
            "GROUP BY p.id, p.coIdx, p.poTitle, p.poContent, p.poDate, p.poDeadline, p.poImgurl, p.poSal, p.poWorkhour, p.poSubType, p.poAddr, p.poEmail, p.poFax, p.poState")
    List<PostCountApplyDto> findAllWithApplyCount(Integer coIdx);
}
