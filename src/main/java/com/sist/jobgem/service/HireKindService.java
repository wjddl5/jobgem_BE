package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.jobgem.repository.HireKindRepository;
import com.sist.jobgem.dto.HireKindDto;
import com.sist.jobgem.entity.HireKind;
import com.sist.jobgem.mapper.HireKindMapper;

import java.util.List;

@Service
public class HireKindService {

    @Autowired
    private HireKindRepository hireKindRepository;

    public List<HireKindDto> getHir() {
        List<HireKind> list = hireKindRepository.findAll();
        return HireKindMapper.INSTANCE.toDtoList(list);
    }

    public boolean addHir(String itemName) {
        HireKindDto eDto = new HireKindDto();
        eDto.setHkName(itemName);

        HireKind e = HireKindMapper.INSTANCE.toEntity(eDto);

        return hireKindRepository.save(e) != null;
    }

    public boolean removeHir(int id) {
        try {
            hireKindRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean editHir(int id, String editItemName) {
        return hireKindRepository.editHir(id, editItemName) > 0;
    }

    public List<HireKind> findByIdIn(List<Integer> hkIdx) {
        return hireKindRepository.findByIdIn(hkIdx);
    }
}
