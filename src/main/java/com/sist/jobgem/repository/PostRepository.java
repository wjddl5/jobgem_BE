package com.sist.jobgem.repository;

import com.sist.jobgem.dto.PostCountApplyDto;
import com.sist.jobgem.dto.PostDto;
import com.sist.jobgem.entity.Post;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>, PostRepositoryCustom {
    @Query("SELECT new com.sist.jobgem.dto.PostDto(t) FROM Post t WHERE t.poState =  1")
    Slice<PostDto> getPostListSlice(Pageable pageable);
    
    int countByCoIdxAndPoState(int coIdx, int poState);

    @Query("SELECT new com.sist.jobgem.dto.PostCountApplyDto(p.id, p.coIdx, p.poTitle, p.poContent, p.poDate, p.poDeadline, p.poImgurl, p.poSal, p.poWorkhour, p.poSubType, p.poAddr, p.poEmail, p.poFax, p.poState, CAST(COUNT(a) AS INTEGER) as applyCount) "
            +
            "FROM Post p LEFT JOIN Applyment a ON p.id = a.poIdx " +
            "WHERE p.coIdx = :coIdx " +
            "GROUP BY p.id, p.coIdx, p.poTitle, p.poContent, p.poDate, p.poDeadline, p.poImgurl, p.poSal, p.poWorkhour, p.poSubType, p.poAddr, p.poEmail, p.poFax, p.poState")
    List<PostCountApplyDto> findAllWithApplyCount(@Param("coIdx") Integer coIdx);

    int countByPoStateAndCoIdx(int poState, int coIdx);

    int countByCoIdx(int coIdx);

    int countByCoIdxAndPoDeadline(int coIdx, LocalDate poDeadline);

    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.poState = 2 WHERE p.poDeadline <= CURRENT_DATE")
    void updateStateByDeadline();

    @Query("SELECT p FROM Post p WHERE p.id = :id AND p.coIdx != 0")
    Post findById(@Param("id") int id);

    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.company WHERE p.id = :id")
    PostDto findByIdWithCompany(@Param("id") int id);
    
    @Query("SELECT p.poTitle FROM Post p WHERE p.id = :id")
    String findTitleById(@Param("id")  int id);
}
