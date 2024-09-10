package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.ApplymentDto;
import com.sist.jobgem.entity.Applyment;
import com.sist.jobgem.mapper.ApplymentMapper;
import com.sist.jobgem.repository.ApplymentRepository;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class ApplymentService {
    @Autowired
    private ApplymentRepository applymentRepository;

    @Autowired
    private ResumeService resumeService;

    public Page<ApplymentDto> getApplymentList(int id, Pageable pageable) {

        Page<Applyment> applymentList = applymentRepository.findByJoIdxAndApState(id, 1, pageable);

        List<ApplymentDto> applymentDtoList = applymentList.getContent().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        // 변환된 DtoList를 사용하여 새로운 Page<ReviewDto> 객체를 생성
        return new PageImpl<>(applymentDtoList, pageable, applymentList.getTotalElements());
    }

    private ApplymentDto convertToDto(Applyment applyment) {
        return ApplymentDto.fromEntity(applyment);
    }

    public Applyment addApplyment(ApplymentDto applymentDto, int joIdx) {
        int reIdx = resumeService.getReDefaultResumeIdx(joIdx);
        applymentDto.setReIdx(reIdx);
        applymentDto.setApState(1);
        return applymentRepository.save(ApplymentMapper.INSTANCE.toEntity(applymentDto));
    }

}
