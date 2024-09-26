package com.sist.jobgem.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.jobgem.entity.Log;
import com.sist.jobgem.repository.LogRepository;

@Service
public class LogService {

    @Autowired
    private LogRepository crudLogRepository; // 로그 저장용 레포지토리

    // 로그를 저장하는 메서드
    public void logAction(Integer usIdx, Integer usType, String loContent) {
        Log log = new Log();
        log.setUsIdx(usIdx); // 함수 사용한 유저의 idx값
        log.setUsType(usType); // 함수 사용한 유저의 타입값
        log.setLoContent(loContent); // 자세한 내용
        log.setLoTime(LocalDateTime.now()); // 현재 시간으로 loTime 설정

        crudLogRepository.save(log); // 로그 저장
    }
}