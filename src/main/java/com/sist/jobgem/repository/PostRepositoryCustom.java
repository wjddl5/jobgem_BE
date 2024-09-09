package com.sist.jobgem.repository;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.sist.jobgem.dto.PostCountApplyDto;

@Repository
public interface PostRepositoryCustom {
    List<PostCountApplyDto> findByFilterWithApplyCount(Map<String, Object> map);
}