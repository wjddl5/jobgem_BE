package com.sist.jobgem.repository;

import com.sist.jobgem.entity.Jobseeker;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

public interface JobseekerRepositoryCustom {

    List<Jobseeker> findJobseekersNotInBlock(Map<String, Object> params);

    Page<Jobseeker> findByTypeAndValueContaining(Map<String, Object> params);
}