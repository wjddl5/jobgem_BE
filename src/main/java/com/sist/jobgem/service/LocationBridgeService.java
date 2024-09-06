package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.LocationBridgeDto;
import com.sist.jobgem.entity.LocationBridge;
import com.sist.jobgem.mapper.LocationBridgeMapper;
import com.sist.jobgem.repository.LocationBridgeRepository;

@Service
public class LocationBridgeService {

    @Autowired
    private LocationBridgeRepository locationBridgeRepository;



    public LocationBridgeDto create(LocationBridgeDto locationBridgeDto) {
        LocationBridge locationBridge = LocationBridgeMapper.INSTANCE.toEntity(locationBridgeDto);
        locationBridge = locationBridgeRepository.save(locationBridge);
        return LocationBridgeMapper.INSTANCE.toDto(locationBridge);
    }
}   