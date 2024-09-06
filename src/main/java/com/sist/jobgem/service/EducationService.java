package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.jobgem.dto.EducationDto;
import com.sist.jobgem.entity.Education;
import com.sist.jobgem.mapper.EducationMapper;
import com.sist.jobgem.repository.EducationRepository;
import java.util.List;

@Service
public class EducationService {

  @Autowired
  private EducationRepository educationRepository;

  public List<EducationDto> getEdu() {
    List<Education> list = educationRepository.findAll();
    return EducationMapper.INSTANCE.toDtoList(list);
  }

  public boolean addEdu(String itemName) {
    EducationDto eDto = new EducationDto();
    eDto.setEdName(itemName);

    Education e = EducationMapper.INSTANCE.toEntity(eDto);

    return educationRepository.save(e) != null;
  }

  public boolean removeEdu(int id) {
    try {
      educationRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Transactional
  public boolean editEdu(int id, String editItemName) {
    return educationRepository.editEdu(id, editItemName) > 0;
  }

  public List<Education> findByIdIn(List<Integer> edIdx) {
    return educationRepository.findByIdIn(edIdx);
  }
}
