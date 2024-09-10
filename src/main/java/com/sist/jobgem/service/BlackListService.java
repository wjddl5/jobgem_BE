package com.sist.jobgem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.BlackListDto;
import com.sist.jobgem.dto.BoardDto;
import com.sist.jobgem.entity.Blacklist;
import com.sist.jobgem.entity.Board;
import com.sist.jobgem.repository.blacklistRepository;

@Service
public class BlackListService {

  @Autowired
  blacklistRepository blacklistRepository;

  public Page<BlackListDto> getAllList(int boType, int boStatus, Pageable pageable, String searchType,
      String searchValue, String selectType) {
    Page<Blacklist> blPage = null;

    Pageable pageable2 = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
        Sort.by(Sort.Direction.DESC, "id"));

    if (searchValue == null) {
      blPage = blacklistRepository.findByStatus(boType, boStatus, pageable2, selectType);
    } else {
      blPage = blacklistRepository.findByWithSearch(
          boType, boStatus, pageable2, searchType, searchValue, selectType);
    }

    // Entity -> Dto 변환
    List<BlackListDto> BlackListDto_list = blPage.getContent().stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());

    return new PageImpl<>(BlackListDto_list, pageable2, blPage.getTotalElements());
  }

}
