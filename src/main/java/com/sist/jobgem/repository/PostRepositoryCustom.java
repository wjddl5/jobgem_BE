package com.sist.jobgem.repository;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.sist.jobgem.dto.PostCountApplyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface PostRepositoryCustom {
    Page<PostCountApplyDto> findByFilterWithApplyCount(Map<String, Object> map, Pageable pageable);

}