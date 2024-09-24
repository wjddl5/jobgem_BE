package com.sist.jobgem.service;

import com.sist.jobgem.dto.BlockResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.BlockDto;
import com.sist.jobgem.mapper.BlockMapper;
import com.sist.jobgem.repository.BlockRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BlockService {
    @Autowired
    private BlockRepository blockRepository;

    public Page<BlockDto> findAllJobseekerBlocks(Pageable pageable, String type, String value) {
        if (type == null && value == null)
            return blockRepository.blackjobseeker(pageable).map(BlockMapper.INSTANCE::toDto);
        else {
            return blockRepository.findByTypeAndValuejobseekerContaining(type, value, pageable)
                    .map(BlockMapper.INSTANCE::toDto);
        }
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

    public Page<BlockDto> findAllCompanyBlocks(Pageable pageable, String type, String value) {
        if (type == null || value == null)
            return blockRepository.blackcompany(pageable).map(BlockMapper.INSTANCE::toDto);
        else {
            return blockRepository.findByTypeAndValuecompanyContaining(type, value, pageable)
                    .map(BlockMapper.INSTANCE::toDto);
        }
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
