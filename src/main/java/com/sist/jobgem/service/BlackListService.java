package com.sist.jobgem.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.jobgem.dto.BlackListDto;
import com.sist.jobgem.entity.Blacklist;
import com.sist.jobgem.repository.BlacklistRepository;

@Service
public class BlackListService {

  @Autowired
  BlacklistRepository blacklistRepository;

  private BlackListDto convertToDto(Blacklist blacklist) {
    return BlackListDto.toDto(blacklist);
  }

  public Page<BlackListDto> getList(int blState, Pageable pageable, String searchType,
      String searchValue, String selectType) {
    Page<Blacklist> blPage = null;

    Pageable pageable2 = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
        Sort.by(Sort.Direction.DESC, "id"));

    switch (selectType) {
      case "all":
        if (searchValue == null) {
          blPage = blacklistRepository.findByStatus(blState, pageable2);
        } else {
          blPage = blacklistRepository.findByWithSearch(
              blState, pageable2, searchType, searchValue);
        }
        break;

      case "company":
        if (searchValue == null) {
          blPage = blacklistRepository.findByStatusAndCompany(blState, pageable2);
        } else {
          blPage = blacklistRepository.findByWithSearchAndCompany(
              blState, pageable2, searchType, searchValue);
        }
        break;

      case "jobseeker":
        if (searchValue == null) {
          blPage = blacklistRepository.findByStatusAndJobseeker(blState, pageable2);
        } else {
          blPage = blacklistRepository.findByWithSearchAndJobseeker(
              blState, pageable2, searchType, searchValue);
        }
        break;
    }

    // Entity -> Dto 변환
    List<BlackListDto> BlackListDto_list = blPage.getContent().stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());

    return new PageImpl<>(BlackListDto_list, pageable2, blPage.getTotalElements());
  }

  public BlackListDto getView(int id) {
    Optional<Blacklist> ob = blacklistRepository.findById(id);
    Blacklist b = ob.get();
    BlackListDto bDto = convertToDto(b);
    return bDto;
  }

  @Transactional
  public boolean removeBlackList(int id) {
    return blacklistRepository.updateStateById(id) == 1;
  }

  @Transactional
  public boolean updateProcess(int id, int nowProcess) {
    int c = 0;
    switch (nowProcess) {
      case 0:
        c = blacklistRepository.updateProcessYes(id);
        break;
      case 1:
        c = blacklistRepository.updateProcessNo(id);
        break;
    }
    if (c == 1)
      return true;
    else
      return false;
  }

}
