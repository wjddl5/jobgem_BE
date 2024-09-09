package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.WorkSchedulesDto;
import com.sist.jobgem.entity.WorkSchedules;
import com.sist.jobgem.mapper.WorkSchedulesMapper;
import com.sist.jobgem.repository.WorkSchedulesRepository;
@Service
public class WorkSchedulesService {
    @Autowired
    private WorkSchedulesRepository workSchedulesRepository;
    
    public WorkSchedules create(WorkSchedulesDto workSchedulesDto) {
        WorkSchedules workSchedules = WorkSchedulesMapper.INSTANCE.toEntity(workSchedulesDto);
        return workSchedulesRepository.save(workSchedules);
    }
}
