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

    public CompanyIndexDto getCompany(Integer id) {
        CompanyIndexDto dto = new CompanyIndexDto();
        if(companyRepository.findById(id).isPresent()){
            dto.setCompany(CompanyMapper.INSTANCE.toDto(companyRepository.findById(id).get()));
        }

        dto.setPostCount(CompanyMapper.INSTANCE.toDto(companyRepository.findById(id).get()).getPosts().size());

        return dto;
    }

}
