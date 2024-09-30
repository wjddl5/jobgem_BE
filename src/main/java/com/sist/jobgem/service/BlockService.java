package com.sist.jobgem.service;

import com.sist.jobgem.dto.BlockResponseDto;
import com.sist.jobgem.dto.JobseekerDto;
import com.sist.jobgem.entity.Jobseeker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.BlockDto;
import com.sist.jobgem.mapper.BlockMapper;
import com.sist.jobgem.mapper.JobseekerMapper;
import com.sist.jobgem.repository.BlockRepository;
import com.sist.jobgem.repository.CompanyRepository;
import com.sist.jobgem.repository.JobseekerRepository;

import jakarta.transaction.Transactional;
import com.sist.jobgem.entity.Block;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class BlockService {
    @Autowired
    private BlockRepository blockRepository;


    public Page<BlockDto> findAllJobseekerBlocks(Map<String,Object> params) {
        Page<Block> jobseekerPage = blockRepository.findByJobseeker(params);
        List<BlockDto> jobseekerList = BlockMapper.INSTANCE.toDtoList(jobseekerPage.getContent());
        return new PageImpl<>(jobseekerList, jobseekerPage.getPageable(), jobseekerPage.getTotalElements());
    }

    // 그냥 리스트
    public List<BlockResponseDto> getBlockListByCoIdx(int coIdx) {
        return blockRepository.findAllByCoIdx(coIdx);
    }

    // 이름 검색있는 리스트
    public Page<BlockDto> getBlockListByCoIdxAndJoName(int coIdx, String name, Pageable pagable) {
        return blockRepository.findAllByCoIdxAndJoName(coIdx, name, pagable);
    }

    // 차단 삭제
    public void deleteBlock(int[] blockList) {
        for (int id : blockList) {
            blockRepository.deleteById(id);
        }
    }

    public Page<BlockDto> findAllCompanyBlocks(Map<String,Object> params) {
       Page<Block> companyPage = blockRepository.findByCompany(params);
       List<BlockDto> companyList = BlockMapper.INSTANCE.toDtoList(companyPage.getContent());
       return new PageImpl<>(companyList, companyPage.getPageable(), companyPage.getTotalElements());
    }

    public void addjobseekerBlock(int id, String value) {
        BlockDto dto = new BlockDto();
        dto.setBlDate(LocalDate.now());
        dto.setJoIdx(id);
        dto.setBlContent(value);
        blockRepository.save(BlockMapper.INSTANCE.ToEntity(dto));
    }

    public void addcompanyBlock(int id, String value) {
        BlockDto dto = new BlockDto();
        dto.setBlDate(LocalDate.now());
        dto.setCoIdx(id);
        dto.setBlContent(value);
        blockRepository.save(BlockMapper.INSTANCE.ToEntity(dto));
    }

    @Transactional
    public boolean deletecomjobBlock(int id) {
        int chk = blockRepository.deletecomjobBlock(id);
        if (chk == 1)
            return true;
        else
            return false;
    }

}
