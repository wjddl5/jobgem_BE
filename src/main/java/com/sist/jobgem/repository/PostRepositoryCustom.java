package com.sist.jobgem.repository;

import java.util.Map;
import org.springframework.stereotype.Repository;

import com.sist.jobgem.dto.PostCountApplyDto;
import com.sist.jobgem.dto.RecruitRequest;
import com.sist.jobgem.entity.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@Repository
public interface PostRepositoryCustom {

    Page<PostCountApplyDto> findByFilterWithApplyCount(Map<String, Object> map, Pageable pageable);
    
    Slice<Post> findByRecruit(RecruitRequest request, Pageable pageable);
}