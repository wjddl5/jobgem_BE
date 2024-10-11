package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.jobgem.repository.HireKindRepository;
import com.sist.jobgem.repository.HkBridgeRepository;
import com.sist.jobgem.dto.HireKindDto;
import com.sist.jobgem.entity.HireKind;
import com.sist.jobgem.mapper.HireKindMapper;

import java.util.List;

@Service
public class HireKindService {

    @Autowired
    private HireKindRepository hireKindRepository;

    @Autowired
    private HkBridgeRepository hkBridgeRepository;

    public List<HireKindDto> getHir() {
        List<HireKind> list = hireKindRepository.findAll();
        return HireKindMapper.INSTANCE.toDtoList(list);
    }

    public boolean addHir(String itemName) {
        HireKindDto eDto = new HireKindDto();

        Integer i = hireKindRepository.findByHkName(itemName);

        if (i == null) {
            eDto.setHkName(itemName);
            HireKind e = HireKindMapper.INSTANCE.toEntity(eDto);
            return hireKindRepository.save(e) != null;
        } else {
            return false;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteHir(int id) {
        try {
            hkBridgeRepository.deleteByHkIdx(id);
            hireKindRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean updateHir(int id, String editItemName) {
        String itemName = editItemName;
        Integer i = hireKindRepository.findByHkName(itemName);

        if (i == null) {
            return hireKindRepository.updateHir(id, editItemName) > 0;
        } else {
            return false;
        }

    }
}
