package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
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
}
