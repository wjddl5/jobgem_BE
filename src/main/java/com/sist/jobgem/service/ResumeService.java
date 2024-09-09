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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResumeService {
    @Autowired
    ResumeRepository resumeRepository;

    public Page<ResumeDto> getResumeList(int id, Pageable pageable) {
        Pageable pageable2 = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));

        Page<Resume> resumeList = resumeRepository.findByJoIdxAndReState(id, 1, pageable2);

        // Review -> ReviewDto 변환
        List<ResumeDto> resumeDtoList = resumeList.getContent().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        // 변환된 DtoList를 사용하여 새로운 Page<ReviewDto> 객체를 생성
        return new PageImpl<>(resumeDtoList, pageable2, resumeList.getTotalElements());
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

}
