package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.jobgem.repository.HaveSkillRepository;
import com.sist.jobgem.repository.SkillBridgeRepository;
import com.sist.jobgem.repository.SkillRepository;
import com.sist.jobgem.dto.SkillDto;
import com.sist.jobgem.entity.Skill;
import com.sist.jobgem.mapper.SkillMapper;

import java.util.List;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private SkillBridgeRepository skillBridgeRepository;

    @Autowired
    private HaveSkillRepository haveSkillRepository;

    public List<SkillDto> getSki() {
        List<Skill> list = skillRepository.findAll();
        return SkillMapper.INSTANCE.toDtoList(list);
    }

    public boolean addSki(String itemName) {
        SkillDto eDto = new SkillDto();
        Integer i = skillRepository.findBySkName(itemName);

        if (i == null) {
            eDto.setSkName(itemName);
            Skill e = SkillMapper.INSTANCE.toEntity(eDto);
            return skillRepository.save(e) != null;
        } else {
            return false;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteSki(int id) {
        try {
            haveSkillRepository.deleteBySkIdx(id);
            skillBridgeRepository.deleteBySkIdx(id);
            skillRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean updateSki(int id, String editItemName) {
        String itemName = editItemName;
        Integer i = skillRepository.findBySkName(itemName);

        if (i == null) {
            return skillRepository.updateSki(id, editItemName) > 0;
        } else {
            return false;
        }
    }
}
