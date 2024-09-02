package com.sist.jobgem.service;

import com.sist.jobgem.dto.CompanyIndexDto;
import com.sist.jobgem.mapper.CompanyMapper;
import com.sist.jobgem.repository.CompanyRepository;
import com.sist.jobgem.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PostRepository postRepository;

    public CompanyIndexDto getCompany(int id) {
        CompanyIndexDto dto = new CompanyIndexDto();
        dto.setCompany(CompanyMapper.INSTANCE.toDto(companyRepository.findById(id).orElseThrow()));

        dto.setPostCount(postRepository.countByCoIdxAndPoState(id, 1));
        dto.setNoPostCount(postRepository.countByCoIdxAndPoState(id, 0));
        return dto;
    }

}
