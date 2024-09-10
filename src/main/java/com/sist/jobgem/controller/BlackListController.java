package com.sist.jobgem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.jobgem.dto.BlackListDto;
import com.sist.jobgem.service.BlackListService;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api/blackList")
public class BlackListController {

  @Autowired
  BlackListService blackListService;

  @RequestMapping("/list")
  public Page<BlackListDto> requestMethodName(Pageable pageable,
      @RequestParam(value = "searchType", required = false) String searchType,
      @RequestParam(value = "searchValue", required = false) String searchValue,
      @RequestParam(value = "selectType", required = false) String selectType) {
    return blackListService.getAllList(0, 0, pageable, searchType, searchValue, selectType);
  }

}
