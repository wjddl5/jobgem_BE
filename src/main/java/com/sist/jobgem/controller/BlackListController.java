package com.sist.jobgem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.jobgem.dto.BlackListDto;
import com.sist.jobgem.service.BlackListService;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("api/blackList")
public class BlackListController {

  @Autowired
  BlackListService blackListService;

  @RequestMapping("/list")
  public Page<BlackListDto> getList(Pageable pageable,
      @RequestParam(value = "searchType", required = false) String searchType,
      @RequestParam(value = "searchValue", required = false) String searchValue,
      @RequestParam(value = "selectType", required = false) String selectType) {
    return blackListService.getList(1, pageable, searchType, searchValue, selectType);
  }

  @RequestMapping("/view")
  public BlackListDto view(@RequestParam(value = "id") int id) {
    return blackListService.getView(id);
  }

  @RequestMapping("/remove")
  public boolean removeOne(@RequestParam(value = "id") int id) {
    return blackListService.removeBlackList(id);
  }

  @RequestMapping("/removeList")
  public boolean removeList(@RequestParam(value = "chkList") List<String> chkList) {
    for (int i = 0; i < chkList.size(); i++) {
      Boolean chk = blackListService.removeBlackList(Integer.parseInt(chkList.get(i)));
      if (!chk)
        return false;
    }
    return true;
  }

  @RequestMapping("/process")
  public boolean updateProcess(@RequestParam(value = "id") int id, @RequestParam(value = "nowProcess") int nowProcess) {
    return blackListService.updateProcess(id, nowProcess);
  }

}
