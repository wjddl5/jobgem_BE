package com.sist.jobgem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sist.jobgem.dto.EducationDto;
import com.sist.jobgem.service.EducationService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

  @Autowired
  private EducationService educationService;

  @RequestMapping("/edu")
  public List<EducationDto> getEdu() {
    return educationService.getEdu();
  }

  @RequestMapping("/addEdu")
  public boolean addEdu(@RequestParam(value = "itemName") String itemName) {
    return educationService.addEdu(itemName);
  }

  @RequestMapping("removeList")
  public boolean removeBbsList(@RequestParam(value = "chkList") List<String> chkList) {
    for (int i = 0; i < chkList.size(); i++) {
      Boolean chk = educationService.removeEdu(Integer.parseInt(chkList.get(i)));
      if (!chk)
        return false;
    }
    return true;
  }

  @RequestMapping("edit")
  public boolean editEdu(@RequestParam(value = "id") int id,
      @RequestParam(value = "editItemName") String editItemName) {
    return educationService.editEdu(id, editItemName);
  }

}
