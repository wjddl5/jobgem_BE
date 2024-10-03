package com.sist.jobgem.service;

import com.sist.jobgem.dto.AlertRequestDto;
import com.sist.jobgem.dto.AlertResponseDto;
import com.sist.jobgem.entity.Alert;
import com.sist.jobgem.mapper.AlertMapper;
import com.sist.jobgem.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class AlertService {
    @Autowired
    private AlertRepository alertRepository;

    public List<AlertResponseDto> findAllByUsIdx(int usIdx){
        List<Alert> list = alertRepository.findAllByUsIdx(usIdx);
        return AlertMapper.INSTANCE.toDto(list);
    }

    public void readAlerts(int usIdx) {
        List<AlertResponseDto> list = findAllByUsIdx(usIdx);
        for(AlertResponseDto dto : list){
            dto.setAlIsRead(1);
            alertRepository.save(AlertMapper.INSTANCE.toEntity(dto));
        }
    }
    public Integer addAlert(int usIdx, String alContent) {
        AlertRequestDto dto = AlertRequestDto.builder()
                .usIdx(usIdx)
                .alContent(alContent)
                .alIsRead(0)
                .alState(1)
                .alDate(Instant.now())
                .build();
        return alertRepository.save(AlertMapper.INSTANCE.toEntity(dto)).getId();
    }

}
