package com.sist.jobgem.service;

import com.sist.jobgem.dto.CompanyDto;
import com.sist.jobgem.dto.CompanyIndexDto;
import com.sist.jobgem.dto.JobseekerDto;
import com.sist.jobgem.dto.TalentDto;
import com.sist.jobgem.mapper.CompanyMapper;
import com.sist.jobgem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private ChatroomRepository chatroomRepository;

    public CompanyIndexDto getCompany(int id) {
        return CompanyIndexDto.builder()
                .company(CompanyMapper.INSTANCE.toDto(companyRepository.findById(id).orElseThrow()))
                .postCount(postRepository.countByCoIdxAndPoState(id, 1))
                .noPostCount(postRepository.countByCoIdxAndPoState(id, 0))
                .reviewCount(reviewRepository.countByCoIdxAndReState(id, 1))
                .interviewCount(interviewRepository.countByCoIdxAndInState(id, 1))
                .talentCount(talentRepository.countByCoIdx(id))
                .fitJobseekerCount(jobseekerRepository.countByWithfitJobseeker(id))
                .blockList(blockRepository.findAllByCoIdx(id))
                .chatList(chatroomRepository.findByOpIdx(id))
                .build();
    }

    public Page<CompanyDto> getCompanyList(Pageable pageable, String value, String type) {
        if (value == null && type == null) {
            return companyRepository.findAll(pageable).map(CompanyMapper.INSTANCE::toDto);
        }
        return companyRepository.findByTypeAndValueContaining(type, value, pageable)
                .map(CompanyMapper.INSTANCE::toDto);
    }

    public Slice<JobseekerDto> getFitJobseekerList(int id, Pageable pageable) {
        return jobseekerRepository.findByWithfitJobseeker(id, pageable);
    }

    public Slice<TalentDto> getWishjobseekerList(int id, Pageable pageable) {
        Slice<TalentDto> talentList = talentRepository.findByCoIdx(id, pageable);
        return talentList;
    }

    public List<CompanyDto> notBlack(String type, String value) {
        if (value == null && type == null) {
            return CompanyMapper.INSTANCE.toDtoList(companyRepository.findAllcompanysNotInBlock());
        }else{
        return CompanyMapper.INSTANCE.toDtoList(companyRepository.findCompanysNotInBlock(type, value));
        }
    }

}