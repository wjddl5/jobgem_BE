package com.sist.jobgem.service;

import com.sist.jobgem.dto.FitJobseekerDto;
import com.sist.jobgem.repository.HaveSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.*;

import com.sist.jobgem.dto.JobseekerDto;
import com.sist.jobgem.entity.Jobseeker;
import com.sist.jobgem.entity.Skill;
import com.sist.jobgem.entity.User;
import com.sist.jobgem.mapper.JobseekerMapper;
import com.sist.jobgem.repository.JobseekerRepository;
import com.sist.jobgem.repository.SkillRepository;
import com.sist.jobgem.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class JobseekerService {
    @Autowired
    private JobseekerRepository jobseekerRepository;
    @Autowired
    private HaveSkillRepository haveSkillRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    UserRepository userRepository;

    public JobseekerDto getJobseeker(int id) {
        JobseekerDto jobseeker = null;

        if (jobseekerRepository.findById(id).isPresent()) {
            jobseeker = JobseekerMapper.INSTANCE.toDto(jobseekerRepository.findById(id).get());
            System.out.println(jobseeker);
        }
        return jobseeker;
    }

    public FitJobseekerDto fitJobseekerList(int id, Pageable pageable) {
        FitJobseekerDto fitJobseeker = FitJobseekerDto.builder()
                .fitJobseekers(jobseekerRepository.findByWithfitJobseeker(id, pageable))
                .build();
        return fitJobseeker;
    }

    public Page<JobseekerDto> getJobseekerList(Pageable pageable, String value, String type) {
        if (value == null && type == null) {
            return jobseekerRepository.findAll(pageable).map(JobseekerMapper.INSTANCE::toDto);
        }
        return jobseekerRepository.findByTypeAndValueContaining(type, value, pageable)
                .map(JobseekerMapper.INSTANCE::toDto);
    }

    @Transactional
    public Jobseeker updateJobseekerDetails(int id, JobseekerDto jobseekerDto) {
        // Jobseeker 존재 여부 확인
        Jobseeker existingJobseeker = jobseekerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jobseeker not found"));

        // 스킬 업데이트
        List<Skill> selectedSkills = new ArrayList<>();
        if (jobseekerDto.getSkillIds() != null && !jobseekerDto.getSkillIds().isEmpty()) {
            selectedSkills = skillRepository.findAllById(jobseekerDto.getSkillIds());
        }

        // 기존 스킬 목록을 지우고 새 스킬 목록으로 대체
        existingJobseeker.getSkills().clear();
        existingJobseeker.getSkills().addAll(selectedSkills);

        // 기본 필드 업데이트 (set 없이 필드 직접 수정)
        existingJobseeker.updateFields(
                jobseekerDto.getJoName(),
                jobseekerDto.getJoBirth(),
                jobseekerDto.getJoAddress(),
                jobseekerDto.getJoTel(),
                jobseekerDto.getJoGender(),
                jobseekerDto.getJoEdu(),
                jobseekerDto.getJoSal(),
                jobseekerDto.getJoImgurl());

        // 변경된 엔티티 저장
        return jobseekerRepository.save(existingJobseeker);
    }

}
