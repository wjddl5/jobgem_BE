package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.CompanyDto;
import com.sist.jobgem.dto.InterviewDto;
import com.sist.jobgem.entity.Company;
import com.sist.jobgem.entity.Interview;
import com.sist.jobgem.mapper.CompanyMapper;
import com.sist.jobgem.mapper.InterviewMapper;
import com.sist.jobgem.repository.CompanyRepository;
import com.sist.jobgem.repository.InterviewRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

@Service
public class InterviewService {
    @Autowired
    InterviewRepository interviewRepository;

    @Autowired
    CompanyRepository companyRepository;

    public Page<InterviewDto> getInterviewist(int id, Pageable pageable) {
        Page<Interview> interviewList = interviewRepository.findByJoIdxAndInState(id, 1, pageable);

        List<InterviewDto> interviewDtoList = interviewList.getContent().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(interviewDtoList, pageable, interviewList.getTotalElements());
    }

    // 로그인한 기업의 면접리뷰
    public Page<InterviewDto> getInterviewListByCoIdx(int id, Pageable pageable) {
        return interviewRepository.findByCoIdx(id, pageable);
    }

    private InterviewDto convertToDto(Interview interview) {
        return InterviewDto.fromEntity(interview);
    }

    public List<CompanyDto> getCompanyList() {
        List<Company> list = companyRepository.findAll();
        return CompanyMapper.INSTANCE.toDtoList(list);
    }

    public Interview addInterview(InterviewDto interview) {
        interview.setInState(1);
        return interviewRepository.save(InterviewMapper.INSTANCE.toEntity(interview));
    }

    public InterviewDto getInterview(int id) {
        Interview interview = interviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));

        return InterviewMapper.INSTANCE.toDto(interview);
    }

    public Interview updateInterview(InterviewDto interviewDto) {
        Interview existingReview = interviewRepository.findById(interviewDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Interview not found"));

        Interview updateInterview = Interview.builder()
                .id(existingReview.getId())
                .joIdx(interviewDto.getJoIdx())
                .coIdx(interviewDto.getCoIdx())
                .inContent(interviewDto.getInContent())
                .inWriteDate(LocalDate.now())
                .inLevel(interviewDto.getInLevel())
                .inState(1)
                .company(existingReview.getCompany())
                .build();

        return interviewRepository.save(updateInterview);
    }

    public int deleteInterview(int id) {
        return interviewRepository.deleteInterview(id);
    }
}
