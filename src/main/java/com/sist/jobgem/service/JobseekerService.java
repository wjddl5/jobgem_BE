package com.sist.jobgem.service;

import com.sist.jobgem.dto.FitJobseekerDto;
import com.sist.jobgem.repository.HaveSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.JobseekerDto;
import com.sist.jobgem.mapper.JobseekerMapper;
import com.sist.jobgem.repository.JobseekerRepository;

@Service
public class JobseekerService {
    @Autowired
    JobseekerRepository jobseekerRepository;
    @Autowired
    private HaveSkillRepository haveSkillRepository;

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
        switch (type) {
            case "joinDate":
            return jobseekerRepository.findByValueContaining(value, pageable).map(JobseekerMapper.INSTANCE::toDto);
            case "leaveDate":
            return jobseekerRepository.findByValueContaining(value, pageable).map(JobseekerMapper.INSTANCE::toDto);
            case "name":
                return jobseekerRepository.findByValueContaining(value, pageable).map(JobseekerMapper.INSTANCE::toDto);
            case "phone":
                return jobseekerRepository.findByValueContaining(value, pageable).map(JobseekerMapper.INSTANCE::toDto);
            case "gender":
                return jobseekerRepository.findByValueContaining(value, pageable).map(JobseekerMapper.INSTANCE::toDto);
            case "birth":
                return jobseekerRepository.findByValueContaining(value, pageable).map(JobseekerMapper.INSTANCE::toDto);
            case "address":
                return jobseekerRepository.findByValueContaining(value, pageable).map(JobseekerMapper.INSTANCE::toDto);
            case "edu":
                return jobseekerRepository.findByValueContaining(value, pageable).map(JobseekerMapper.INSTANCE::toDto);
            case "sal":
                return jobseekerRepository.findByValueContaining(value, pageable).map(JobseekerMapper.INSTANCE::toDto);
            default:
                return jobseekerRepository.findAll(pageable).map(JobseekerMapper.INSTANCE::toDto);
        }
    }
}
