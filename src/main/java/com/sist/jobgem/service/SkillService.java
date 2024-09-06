package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sist.jobgem.repository.SkillRepository;
import com.sist.jobgem.dto.SkillDto;
import com.sist.jobgem.entity.Skill;
import com.sist.jobgem.mapper.SkillMapper;

import java.util.List;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> findByIdIn(List<Integer> skIdx) {
        return skillRepository.findByIdIn(skIdx);
    }

    public List<SkillDto> getSkillList() {
        return SkillMapper.INSTANCE.toDtoList(skillRepository.findAll());
    }

}
