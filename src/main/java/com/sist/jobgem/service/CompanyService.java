package com.sist.jobgem.service;

import com.sist.jobgem.dto.*;
import com.sist.jobgem.entity.Company;
import com.sist.jobgem.mapper.CompanyMapper;
import com.sist.jobgem.mapper.UserMapper;
import com.sist.jobgem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    @Autowired
    private UserRepository userRepository;

    public CompanyIndexDto getCompany(int id) {
        Company company = companyRepository.findById(id).orElseThrow();
        return CompanyIndexDto.builder()
                .company(CompanyMapper.INSTANCE.toDto(companyRepository.findById(id).orElseThrow()))
                .postCount(postRepository.countByCoIdxAndPoState(id, 1))
                .noPostCount(postRepository.countByCoIdxAndPoState(id, 0))
                .reviewCount(reviewRepository.countByCoIdxAndReState(id, 1))
                .interviewCount(interviewRepository.countByCoIdxAndInState(id, 1))
                .talentCount(talentRepository.countByCoIdx(id))
                .fitJobseekerCount(jobseekerRepository.countByWithfitJobseeker(id))
                .blockList(blockRepository.findAllByCoIdx(id))
                .chatList(chatroomRepository.findByOpIdx(company.getUsIdx()))
                .build();
    }

    public Integer updateCompany(int id, String coThumbimgUrl) {
        CompanyDto joinCompany = CompanyMapper.INSTANCE.toDto(companyRepository.findById(id).orElseThrow());
        joinCompany.setCoThumbimgUrl(coThumbimgUrl);

        return companyRepository.save(CompanyMapper.INSTANCE.toEntity(joinCompany)).getId();
    }

    public Integer updateCompany(CompanyDto companyDto) {
        Company company = CompanyMapper.INSTANCE.toEntity(companyDto);
        return companyRepository.save(company).getId();
    }

    public Page<CompanyDto> findAllCompanies(Map<String, Object> params) {
        Page<Company> companyPage = companyRepository.findByTypeAndValueContaining(params);
        List<CompanyDto> companyDtoList = CompanyMapper.INSTANCE.toDtoList(companyPage.getContent());
        return new PageImpl<>(companyDtoList, companyPage.getPageable(), companyPage.getTotalElements());
    }

    public Slice<JobseekerDto> getFitJobseekerList(int id, Pageable pageable) {
        return jobseekerRepository.findByWithfitJobseeker(id, pageable);
    }

    public Slice<TalentDto> getWishjobseekerList(int id, Pageable pageable) {
        Slice<TalentDto> talentList = talentRepository.findByCoIdx(id, pageable);
        return talentList;
    }

    public List<CompanyDto> findUnblockedCompany(Map<String, Object> params) {
        return CompanyMapper.INSTANCE.toDtoList(companyRepository.findCompanysNotInBlock(params));
    }

    public CompanyDto getCompanyById(int id) {
        return CompanyMapper.INSTANCE.toDto(companyRepository.findById(id).orElseThrow());
    }

    public Integer deleteCompany(int id) {
        UserDto user = UserMapper.INSTANCE.toDto(userRepository.findById(id));
        user.setUsState(0);
        return userRepository.save(UserMapper.INSTANCE.toEntity(user)).getId();
    }
}