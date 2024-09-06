package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.jobgem.repository.SkillRepository;
import com.sist.jobgem.dto.SkillDto;
import com.sist.jobgem.entity.Skill;
import com.sist.jobgem.mapper.SkillMapper;

import java.util.List;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    public List<SkillDto> getSki() {
        List<Skill> list = skillRepository.findAll();
        return SkillMapper.INSTANCE.toDtoList(list);
    }

    public boolean addSki(String itemName) {
        SkillDto eDto = new SkillDto();
        eDto.setSkName(itemName);

        Skill e = SkillMapper.INSTANCE.toEntity(eDto);

        return skillRepository.save(e) != null;
    }

    public boolean removeSki(int id) {
        try {
            skillRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean editSki(int id, String editItemName) {
        return skillRepository.editSki(id, editItemName) > 0;
    }

    public List<Skill> findByIdIn(List<Integer> skIdx) {
        return skillRepository.findByIdIn(skIdx);
    }

    public List<SkillDto> getSkillList() {
        return SkillMapper.INSTANCE.toDtoList(skillRepository.findAll());
    }

}
