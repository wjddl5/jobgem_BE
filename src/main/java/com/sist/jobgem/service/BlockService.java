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

    public Page<BlockDto> blackjobseekerList(Pageable pageable, String value, String type) {
        if (value == null && type == null) {
            return blockRepository.blackjobseeker(pageable).map(BlockMapper.INSTANCE::toDto);
        }
        return blockRepository.findByTypeAndValuejobseekerContaining(type, value, pageable).map(BlockMapper.INSTANCE::toDto);
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
        for(int id : blockList){
            blockRepository.deleteById(id);
        }
    }

    public Page<BlockDto> blackcompanyList(Pageable pageable, String value, String type) {
        if (value == null && type == null) {
            return blockRepository.blackcompany(pageable).map(BlockMapper.INSTANCE::toDto);
        }
        return blockRepository.findByTypeAndValuecompanyContaining(type, value, pageable).map(BlockMapper.INSTANCE::toDto);
    }

    public BlockDto addjobseekerBlock(BlockDto dto) {
        dto.setBlDate(LocalDate.now());
        dto.setJoIdx(dto.getJoIdx());
        return BlockMapper.INSTANCE.toDto(blockRepository.save(BlockMapper.INSTANCE.ToEntity(dto)));
    }

    public BlockDto addcompanyBlock(BlockDto dto) {
        dto.setBlDate(LocalDate.now());
        dto.setCoIdx(dto.getCoIdx());
        return BlockMapper.INSTANCE.toDto(blockRepository.save(BlockMapper.INSTANCE.ToEntity(dto)));
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
