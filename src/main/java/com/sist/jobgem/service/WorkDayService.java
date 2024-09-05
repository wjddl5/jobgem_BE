package com.sist.jobgem.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.sist.jobgem.repository.WorkDayRepository;
import com.sist.jobgem.entity.WorkDay;
import java.util.List;

@Service
public class WorkDayService {
    @Autowired
    private WorkDayRepository workDayRepository;

    public List<WorkDay> getWorkIdIn(List<Integer> wdIdx) {
        return workDayRepository.findByIdIn(wdIdx);
    }
}
