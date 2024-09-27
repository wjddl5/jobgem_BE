package com.sist.jobgem.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.ApplymentDto;
import com.sist.jobgem.dto.ApplymentSearchDto;
import com.sist.jobgem.entity.Applyment;
import com.sist.jobgem.mapper.ApplymentMapper;
import com.sist.jobgem.repository.ApplymentRepository;

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

    public Applyment addApplyment(ApplymentDto applyment) {
        int reIdx = resumeService.getReDefaultResumeIdx(applyment.getJoIdx());
        applyment.setReIdx(reIdx);
        applyment.setApRead(0);
        applyment.setApState(1);
        return applymentRepository.save(ApplymentMapper.INSTANCE.toEntity(applyment));
    }

    public boolean isAlreadyApplied(ApplymentDto applyment) {
        return applymentRepository.existsByJoIdxAndPoIdx(applyment.getJoIdx(), applyment.getPoIdx());
    }

    public Map<String, Object> applymentCount(int id) {
        Map<String, Object> map = new HashMap<>();

        map.put("지원완료", applymentRepository.countByJoIdxAndApState(id, 1));
        map.put("열람", applymentRepository.countByJoIdxAndApReadAndApState(id, 1, 1));
        map.put("미열람", applymentRepository.countByJoIdxAndApReadAndApState(id, 0, 1));

        return map;
    }

    public Page<ApplymentDto> searchApplyment(ApplymentSearchDto dto, Pageable pageable) {
        return applymentRepository.searchApplyments(
                dto.getJoIdx(),
                dto.getApRead(),
                dto.getStartDate(),
                dto.getEndDate(),
                pageable);
    }

    public Page<ApplymentDto> getApplymentListByPoIdx(int poIdx, Pageable pageable) {
        Page<Applyment> applymentList = applymentRepository.findByPoIdxAndApState(poIdx, 1, pageable);
        List<ApplymentDto> applymentDtoList = applymentList.getContent().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(applymentDtoList, pageable, applymentList.getTotalElements());
    }

    public void view(int id) {
        applymentRepository.view(id);
    }

    public Page<ApplymentDto> searchApplymentwithJobseeker(ApplymentSearchDto dto, Pageable pageable) {
        return applymentRepository.searchApplymentwithJobseeker(
                dto.getJoIdx(),
                dto.getApRead(),
                dto.getStartDate(),
                dto.getEndDate(),
                pageable);
    }
}
