package com.sist.jobgem.repository;

import java.util.Map;
import java.util.List;
import org.springframework.data.domain.Page;

import com.sist.jobgem.entity.Company;

public interface CompanyRepositoryCustom {

    Page<Company> findByTypeAndValueContaining(Map<String, Object> params);

    List<Company> findCompanysNotInBlock(Map<String, Object> params);

}
