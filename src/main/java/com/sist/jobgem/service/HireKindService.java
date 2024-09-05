package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sist.jobgem.repository.HireKindRepository;
import com.sist.jobgem.entity.HireKind;
import java.util.List;

@Service
public class HireKindService {
    @Autowired
    private HireKindRepository hireKindRepository;

    public List<HireKind> findByIdIn(List<Integer> hkIdx) {
        return hireKindRepository.findByIdIn(hkIdx);
    }
}
