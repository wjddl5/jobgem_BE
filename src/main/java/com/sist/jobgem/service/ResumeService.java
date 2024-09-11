package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import com.sist.jobgem.dto.ResumeDto;
import com.sist.jobgem.entity.Resume;
import com.sist.jobgem.mapper.ResumeMapper;
import com.sist.jobgem.repository.ResumeRepository;

import jakarta.transaction.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResumeService {
    @Autowired
    ResumeRepository resumeRepository;

    public Page<ResumeDto> getResumeList(int id, Pageable pageable) {

        Page<Resume> resumeList = resumeRepository.findByJoIdxAndReState(id, 1, pageable);

        // Review -> ReviewDto 변환
        List<ResumeDto> resumeDtoList = resumeList.getContent().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        // 변환된 DtoList를 사용하여 새로운 Page<ReviewDto> 객체를 생성
        return new PageImpl<>(resumeDtoList, pageable, resumeList.getTotalElements());
    }

    // review 엔티티를 reviewDto로 변환하는 메소드
    private ResumeDto convertToDto(Resume resume) {
        return ResumeDto.fromEntity(resume);
    }

    public Resume addResume(ResumeDto resume) {
        resume.setReState(1);
        return resumeRepository.save(ResumeMapper.INSTANCE.toEntity(resume));
    }

    public ResumeDto getResume(int id) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resume not found"));

        return ResumeMapper.INSTANCE.toDto(resume);
    }

    public Resume updateResume(ResumeDto resumeDto) {
        Resume existingReview = resumeRepository.findById(resumeDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Resume not found"));

        Resume updatedResume = Resume.builder()
                .id(existingReview.getId())
                .joIdx(resumeDto.getJoIdx())
                .reTitle(resumeDto.getReTitle())
                .reContent(resumeDto.getReContent())
                .reFileUrl(resumeDto.getReFileUrl())
                .reWriteDate(LocalDate.now())
                .reState(1)
                .build();

        return resumeRepository.save(updatedResume);
    }

    public int deleteResume(int id) {
        return resumeRepository.deleteResume(id);
    }

    public Integer getReDefaultResumeIdx(Integer joIdx) {
        return resumeRepository.findByJoIdxAndReDefault(joIdx, 1)
                .map(Resume::getId) // re_idx 반환
                .orElseThrow(() -> new RuntimeException("기본 이력서를 찾을 수 없습니다."));
    }

    @Transactional
    public void updateDefaultResume(int resumeId, int joIdx) {
        // 1. joIdx에 해당하는 모든 이력서의 reDefault 값을 0으로 설정
        resumeRepository.resetDefaultResume(joIdx);

        // 2. resumeId에 해당하는 이력서를 reDefault = 1로 설정
        resumeRepository.setDefaultResume(resumeId);
    }
    
}
