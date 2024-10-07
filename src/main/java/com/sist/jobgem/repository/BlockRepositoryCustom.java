package com.sist.jobgem.repository;

import org.springframework.data.domain.Page;

import com.sist.jobgem.entity.Block;

import java.util.Map;

public interface BlockRepositoryCustom {
    Page<Block> findByJobseeker(Map<String, Object> params);
    Page<Block> findByCompany(Map<String, Object> params);
}
