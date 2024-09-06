package com.sist.jobgem.service;

import com.sist.jobgem.dto.TalentDto;
import com.sist.jobgem.entity.Talent;
import com.sist.jobgem.mapper.TalentMapper;
import com.sist.jobgem.repository.TalentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TalentService {
    @Autowired
    private TalentRepository talentRepository;

    public int addTalent(TalentDto request) {
        Talent talent = TalentMapper.INSTANCE.toEntity(request);
        return talentRepository.save(talent).getId();
    }

    public void removeTalent(int id) {
        talentRepository.deleteById(id);
    }

}