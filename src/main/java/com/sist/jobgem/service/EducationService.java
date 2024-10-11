package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.jobgem.dto.EducationDto;
import com.sist.jobgem.entity.Education;
import com.sist.jobgem.mapper.EducationMapper;
import com.sist.jobgem.repository.EducationBridgeRepository;
import com.sist.jobgem.repository.EducationRepository;
import java.util.List;

@Service
public class EducationService {

  @Autowired
  private EducationRepository educationRepository;

  @Autowired
  private EducationBridgeRepository educationBridgeRepository;

  public List<EducationDto> getEdu() {
    List<Education> list = educationRepository.findAll();
    return EducationMapper.INSTANCE.toDtoList(list);
  }

  public boolean addEdu(String itemName) {
    EducationDto eDto = new EducationDto();

    Integer i = educationRepository.findByEdName(itemName);

    if (i == null) {
      eDto.setEdName(itemName);
      Education e = EducationMapper.INSTANCE.toEntity(eDto);

      return educationRepository.save(e) != null;

    } else {
      return false;
    }
  }

  @Transactional(rollbackFor = Exception.class)
  public boolean deleteEdu(int id) {
    try {
      educationBridgeRepository.deleteByEdIdx(id);
      educationRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Transactional
  public boolean updateEdu(int id, String editItemName) {
    String itemName = editItemName;
    Integer i = educationRepository.findByEdName(itemName);

    if (i == null) {
      return educationRepository.updateEdu(id, editItemName) > 0;

    } else {
      return false;
    }

  }
}
