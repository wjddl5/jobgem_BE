package com.sist.jobgem.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.ScrapDto;
import com.sist.jobgem.dto.ScrapResponse;
import com.sist.jobgem.entity.Scrap;
import com.sist.jobgem.mapper.ScrapMapper;
import com.sist.jobgem.repository.ScrapRepository;

@Service
public class ScrapService {
    @Autowired
    private ScrapRepository scrapRepository;

    public boolean isScrapByJoIdxAndPoIdx(Integer joIdx, Integer poIdx) {
        Scrap scrap = scrapRepository.findByJoIdxAndPoIdx(joIdx, poIdx);
        if (scrap != null) {
            return true;
        } else {
            return false;
        }
    }

    public String managementScrap(Integer joIdx, Integer poIdx) {
        Scrap scrap = scrapRepository.findByJoIdxAndPoIdx(joIdx, poIdx);
        if (scrap == null) {
            ScrapDto scrapDto = new ScrapDto();
            scrapDto.setJoIdx(joIdx);
            scrapDto.setPoIdx(poIdx);
            scrapDto.setScDate(LocalDate.now());
            scrapDto.setScState(1);
            scrapRepository.save(ScrapMapper.INSTANCE.toEntity(scrapDto));
            return "add";
        } 
        scrapRepository.delete(scrap);
        return "delete";
    }

    public List<ScrapResponse> getScrapByJoIdx(Integer joIdx) {
        List<ScrapResponse> responses = ScrapMapper.INSTANCE.toScrapResponseList(scrapRepository.findByJoIdx(joIdx));
        System.out.println(responses);
        return responses;
    }
}   

