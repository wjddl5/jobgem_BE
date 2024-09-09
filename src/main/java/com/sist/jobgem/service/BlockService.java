package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.BlockDto;
import com.sist.jobgem.mapper.BlockMapper;
import com.sist.jobgem.repository.BlockRepository;

import java.util.List;

@Service
public class BlockService {
    @Autowired
    private BlockRepository blockRepository;

    public Page<BlockDto> getblackList(Pageable pageable, String value, String type) {
        if (value == null && type == null) {
            return blockRepository.findAll(pageable).map(BlockMapper.INSTANCE::toDto);
        }
        return blockRepository.findByTypeAndValueContaining(type, value, pageable)
                .map(BlockMapper.INSTANCE::toDto);
    }

    // 그냥 리스트
    public List<BlockDto> getBlockListByCoIdx(int coIdx) {
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
}
