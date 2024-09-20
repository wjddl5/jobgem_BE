package com.sist.jobgem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.jobgem.dto.BlackListDto;
import com.sist.jobgem.service.BlackListService;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/api/blackList")
public class BlackListController {

  @Autowired
  BlackListService blackListService;

  @GetMapping("/list")
  public Page<BlackListDto> getList(Pageable pageable,
      @RequestParam(value = "searchType", required = false) String searchType,
      @RequestParam(value = "searchValue", required = false) String searchValue,
      @RequestParam(value = "selectType", required = false) String selectType) {
    return blackListService.getList(1, pageable, searchType, searchValue, selectType);
  }

  @GetMapping("/{id}")
  public BlackListDto view(@PathVariable int id) {
    return blackListService.getView(id);
  }

  @DeleteMapping("/{id}")
  public boolean removeOne(@PathVariable int id) {
    return blackListService.removeBlackList(id);
  }

  @DeleteMapping("/removeList")
  public boolean removeList(@RequestParam(value = "chkList") List<String> chkList) {
    for (int i = 0; i < chkList.size(); i++) {
      Boolean chk = blackListService.removeBlackList(Integer.parseInt(chkList.get(i)));
      if (!chk)
        return false;
    }
    return true;
  }

  @PutMapping("/process/{id}")
  public boolean updateProcess(@PathVariable int id, @RequestParam(value = "nowProcess") int nowProcess) {
    return blackListService.updateProcess(id, nowProcess);
  }

}
