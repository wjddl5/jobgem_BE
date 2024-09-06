package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sist.jobgem.repository.CareerRepository;
import com.sist.jobgem.entity.Career;
import java.util.List;

@Service
public class CareerService {
    @Autowired
    private CareerRepository careerRepository;

    public List<Career> findByIdIn(List<Integer> crIdx) {
        return careerRepository.findByIdIn(crIdx);
    }
}
