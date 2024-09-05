package com.sist.jobgem.service;

import com.sist.jobgem.entity.Education;
import com.sist.jobgem.repository.EducationRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationService {
    @Autowired
    private EducationRepository educationRepository;

    public List<Education> findByIdIn(List<Integer> edIdx) {
        return educationRepository.findByIdIn(edIdx);
    }
}
