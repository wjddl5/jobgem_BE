package com.sist.jobgem.service;

import com.sist.jobgem.dto.CompanyDto;
import com.sist.jobgem.dto.CompanyIndexDto;
import com.sist.jobgem.dto.JobseekerDto;
import com.sist.jobgem.mapper.CompanyMapper;
import com.sist.jobgem.mapper.JobseekerMapper;
import com.sist.jobgem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private InterviewRepository interviewRepository;
    @Autowired
    private TalentRepository talentRepository;
    @Autowired
    private JobseekerRepository jobseekerRepository;
    @Autowired
    private BlockRepository blockRepository;

    public CompanyIndexDto getCompany(int id, int blockPage) {
        PageRequest blockList = PageRequest.of(blockPage, 1, Sort.by(Sort.Direction.DESC, "blDate"));

        return CompanyIndexDto.builder()
                .company(CompanyMapper.INSTANCE.toDto(companyRepository.findById(id).orElseThrow()))
                .postCount(postRepository.countByCoIdxAndPoState(id, 1))
                .noPostCount(postRepository.countByCoIdxAndPoState(id, 0))
                .reviewCount(reviewRepository.countByCoIdxAndReState(id, 1))
                .interviewCount(interviewRepository.countByCoIdxAndInState(id, 1))
                .talentCount(talentRepository.countByCoIdx(id))
                .fitJobseekerCount(jobseekerRepository.countByWithfitJobseeker(id))
                .blockList(blockRepository.findAllByCoIdx(id, blockList))
                .build();
    }
    public Page<CompanyDto> getCompanyList(Pageable pageable, String value, String type) {
        if (value == null && type == null) {
            return companyRepository.findAll(pageable).map(CompanyMapper.INSTANCE::toDto);
        }
        switch (type) {
            case "name":
            return companyRepository.findByValueContaining(value, pageable).map(CompanyMapper.INSTANCE::toDto);
            case "number":
            return companyRepository.findByValueContaining(value, pageable).map(CompanyMapper.INSTANCE::toDto);
            case "address":
                return companyRepository.findByValueContaining(value, pageable).map(CompanyMapper.INSTANCE::toDto);
            case "tel":
                return companyRepository.findByValueContaining(value, pageable).map(CompanyMapper.INSTANCE::toDto);
            case "type":
                return companyRepository.findByValueContaining(value, pageable).map(CompanyMapper.INSTANCE::toDto);
            case "open":
                return companyRepository.findByValueContaining(value, pageable).map(CompanyMapper.INSTANCE::toDto);
            case "employee":
                return companyRepository.findByValueContaining(value, pageable).map(CompanyMapper.INSTANCE::toDto);
            case "sales":
                return companyRepository.findByValueContaining(value, pageable).map(CompanyMapper.INSTANCE::toDto);
            case "score":
                return companyRepository.findByValueContaining(value, pageable).map(CompanyMapper.INSTANCE::toDto);
            case "managerName":
                return companyRepository.findByValueContaining(value, pageable).map(CompanyMapper.INSTANCE::toDto);
            case "managerTel":
                return companyRepository.findByValueContaining(value, pageable).map(CompanyMapper.INSTANCE::toDto);
            default:
                return companyRepository.findAll(pageable).map(CompanyMapper.INSTANCE::toDto);
        }
    }
}
