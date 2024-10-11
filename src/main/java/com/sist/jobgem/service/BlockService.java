package com.sist.jobgem.service;

import com.sist.jobgem.dto.BlockResponseDto;
import com.sist.jobgem.dto.CompanyDto;
import com.sist.jobgem.dto.JobseekerDto;
import com.sist.jobgem.dto.UserDto;
import com.sist.jobgem.entity.Jobseeker;
import com.sist.jobgem.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.BlockDto;
import com.sist.jobgem.mapper.BlockMapper;
import com.sist.jobgem.mapper.CompanyMapper;
import com.sist.jobgem.mapper.JobseekerMapper;
import com.sist.jobgem.mapper.UserMapper;
import com.sist.jobgem.repository.BlockRepository;
import com.sist.jobgem.repository.CompanyRepository;
import com.sist.jobgem.repository.JobseekerRepository;
import com.sist.jobgem.repository.UserRepository;

import jakarta.transaction.Transactional;
import com.sist.jobgem.entity.Block;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Optional;
@Service
public class BlockService {
    @Autowired
    private BlockRepository blockRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JobseekerRepository jobseekerRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public Page<BlockDto> findAllJobseekerBlocks(Map<String, Object> params) {
        Page<Block> blockPage = blockRepository.findByJobseeker(params);
        List<BlockDto> blockList = blockPage.getContent().stream()
                .map(block -> new BlockDto(block, block.getJobseeker()))
                .collect(Collectors.toList());
        return new PageImpl<>(blockList, blockPage.getPageable(), blockPage.getTotalElements());
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

    public Page<BlockDto> findAllCompanyBlocks(Map<String, Object> params) {
        Page<Block> companyPage = blockRepository.findByCompany(params);
        List<BlockDto> companyList = BlockMapper.INSTANCE.toDtoList(companyPage.getContent());
        return new PageImpl<>(companyList, companyPage.getPageable(), companyPage.getTotalElements());
    }
    @Transactional  
    public Block addjobseekerBlock(BlockDto dto) {
        dto.setBlDate(LocalDate.now());
        JobseekerDto jobseekerdto = JobseekerMapper.INSTANCE.toDto(jobseekerRepository.findById(dto.getJoIdx()).orElseThrow(() -> new IllegalArgumentException("Jobseeker not found")));
        UserDto userstate = UserMapper.INSTANCE.toDto(jobseekerdto.getUser());
        userstate.setUsState(2);
        User user2 = UserMapper.INSTANCE.toEntity(userstate);
        userRepository.save(user2);
        Block newBlock = blockRepository.save(BlockMapper.INSTANCE.ToEntity(dto));
        return newBlock;
    }
    @Transactional  
    public Block addcompanyBlock(BlockDto dto) {
        dto.setBlDate(LocalDate.now());
        CompanyDto companydto = CompanyMapper.INSTANCE.toDto(companyRepository.findById(dto.getCoIdx()).orElseThrow(() -> new IllegalArgumentException("Company not found")));
        UserDto userstate = UserMapper.INSTANCE.toDto(companydto.getUser());
        userstate.setUsState(2);
        User user2 = UserMapper.INSTANCE.toEntity(userstate);
        userRepository.save(user2);
        Block newBlock = blockRepository.save(BlockMapper.INSTANCE.ToEntity(dto));
        return newBlock;
    }

    @Transactional
    public boolean deletecomjobBlock(int id) {
        boolean result = false;
        Block block = blockRepository.findById(id);
        if(block != null) {
            if(block.getJoIdx() != null) {
                userRepository.updateUserStateByBlockJobseeker(block.getJoIdx());
            }
            if(block.getCoIdx() != null) {
                userRepository.updateUserStateByBlockCompany(block.getCoIdx());
            }
            blockRepository.delete(block);
            result = true;
        }
        return result;
    }

}
