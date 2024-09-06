package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.BlockDto;
import com.sist.jobgem.mapper.BlockMapper;
import com.sist.jobgem.repository.BlockRepository;

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
}
