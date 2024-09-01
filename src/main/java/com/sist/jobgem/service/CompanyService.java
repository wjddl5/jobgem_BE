package com.sist.jobgem.service;

import com.sist.jobgem.dto.CompanyIndexDto;
import com.sist.jobgem.mapper.CompanyMapper;
import com.sist.jobgem.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyIndexDto getCompany(int id) {
        CompanyIndexDto dto = new CompanyIndexDto();
        dto.setCompany(CompanyMapper.INSTANCE.toDto(companyRepository.findById(id).get()));

        dto.setPostCount(companyRepository.findByIdWithPostsCount(id, 1).get().getPosts().size());
        dto.setNoPostCount(companyRepository.findByIdWithPostsCount(id, 0).get().getPosts().size());
        return dto;
    }

}
