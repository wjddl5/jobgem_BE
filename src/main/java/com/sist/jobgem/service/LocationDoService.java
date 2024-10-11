package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.jobgem.dto.LocationDoDto;
import com.sist.jobgem.entity.LocationDo;
import com.sist.jobgem.mapper.LocationDoMapper;
import com.sist.jobgem.repository.LocationBridgeRepository;
import com.sist.jobgem.repository.LocationDoRepository;
import com.sist.jobgem.repository.LocationGuSiRepository;

import java.util.List;

@Service
public class LocationDoService {

  @Autowired
  private LocationDoRepository locationDoRepository;

  @Autowired
  LocationGuSiRepository locationGuSiRepository;

  @Autowired
  LocationBridgeRepository locationBridgeRepository;

  public List<LocationDoDto> getLoc() {
    List<LocationDo> list = locationDoRepository.findAll();
    return LocationDoMapper.INSTANCE.toDtoList(list);
  }

  public boolean addLoc(String itemName) {
    LocationDoDto lDto = new LocationDoDto();
    Integer i = locationDoRepository.findByLdName(itemName);

    if (i == null) {
      lDto.setName(itemName);
      LocationDo e = LocationDoMapper.INSTANCE.toEntity(lDto);
      return locationDoRepository.save(e) != null;
    } else {
      return false;
    }
  }

  @Transactional(rollbackFor = Exception.class)
  public boolean deleteLoc(int id) {
    try {
      locationBridgeRepository.deleteByLgIdx(id);
      locationGuSiRepository.deleteByLdIdx(id);
      locationDoRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Transactional
  public boolean updateLoc(int id, String editItemName) {
    String itemName = editItemName;
    Integer i = locationDoRepository.findByLdName(itemName);

    if (i == null) {
      return locationDoRepository.updateLoc(id, editItemName) > 0;
    } else {
      return false;
    }
  }
}
