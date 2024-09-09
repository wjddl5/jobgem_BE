package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.jobgem.dto.LocationDoDto;
import com.sist.jobgem.entity.LocationDo;
import com.sist.jobgem.mapper.LocationDoMapper;
import com.sist.jobgem.repository.LocationDoRepository;
import com.sist.jobgem.repository.LocationGuSiRepository;

import java.util.List;

@Service
public class LocationDoService {

  @Autowired
  private LocationDoRepository locationDoRepository;

  @Autowired
  LocationGuSiRepository locationGuSiRepository;

  public List<LocationDoDto> getLoc() {
    List<LocationDo> list = locationDoRepository.findAll();
    return LocationDoMapper.INSTANCE.toDtoList(list);
  }

  public boolean addLoc(String itemName) {
    LocationDoDto lDto = new LocationDoDto();
    lDto.setName(itemName);

    LocationDo e = LocationDoMapper.INSTANCE.toEntity(lDto);

    return locationDoRepository.save(e) != null;
  }

  @Transactional
  public boolean removeLoc(int id) {
    try {
      locationDoRepository.deleteById(id);
      locationGuSiRepository.deleteByLdIdx(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Transactional
  public boolean editLoc(int id, String editItemName) {
    return locationDoRepository.editLoc(id, editItemName) > 0;
  }
}
