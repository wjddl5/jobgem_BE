package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sist.jobgem.repository.LocationGuSiRepository;
import com.sist.jobgem.entity.LocationGuSi;
import java.util.List;

@Service
public class LocationGuSiService {
    @Autowired
    private LocationGuSiRepository locationGuSiRepository;

    public List<LocationGuSi> findByIdIn(List<Integer> lgIdx) {
        return locationGuSiRepository.findByIdIn(lgIdx);
    }
}
