package com.sist.jobgem.service;

import com.sist.jobgem.dto.FitJobseekerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.JobseekerDto;
import com.sist.jobgem.mapper.JobseekerMapper;
import com.sist.jobgem.repository.JobseekerRepository;

@Service
public class JobseekerService {
    @Autowired
    JobseekerRepository jobseekerRepository;

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
}
