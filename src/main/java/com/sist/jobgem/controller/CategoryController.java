package com.sist.jobgem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sist.jobgem.dto.CareerDto;
import com.sist.jobgem.dto.EducationDto;
import com.sist.jobgem.dto.HireKindDto;
import com.sist.jobgem.dto.SkillDto;
import com.sist.jobgem.service.CareerService;
import com.sist.jobgem.service.EducationService;
import com.sist.jobgem.service.HireKindService;
import com.sist.jobgem.service.LocationGuSiService;
import com.sist.jobgem.service.SkillService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

  @Autowired
  private EducationService educationService;

  @Autowired
  private CareerService careerService;

  @Autowired
  private SkillService skillService;

  @Autowired
  private HireKindService hireKindService;

  @Autowired
  private LocationGuSiService locationGuSiService;

  // 학력
  @RequestMapping("/edu")
  public List<EducationDto> getEdu() {
    return educationService.getEdu();
  }

  @RequestMapping("/addEdu")
  public boolean addEdu(@RequestParam(value = "itemName") String itemName) {
    return educationService.addEdu(itemName);
  }

  @RequestMapping("removeList")
  public boolean removeEdu(@RequestParam(value = "chkList") List<String> chkList) {
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

  // 경력
  @RequestMapping("/car")
  public List<CareerDto> getCar() {
    return careerService.getCar();
  }

  @RequestMapping("/addCar")
  public boolean addCar(@RequestParam(value = "itemName") String itemName) {
    return careerService.addCar(itemName);
  }

  @RequestMapping("removeCar")
  public boolean removeCar(@RequestParam(value = "chkList") List<String> chkList) {
    for (int i = 0; i < chkList.size(); i++) {
      Boolean chk = careerService.removeCar(Integer.parseInt(chkList.get(i)));
      if (!chk)
        return false;
    }
    return true;
  }

  @RequestMapping("editCar")
  public boolean editCar(@RequestParam(value = "id") int id,
      @RequestParam(value = "editItemName") String editItemName) {
    return careerService.editCar(id, editItemName);
  }

  // 기술
  @RequestMapping("/ski")
  public List<SkillDto> getSki() {
    return skillService.getSki();
  }

  @RequestMapping("/addSki")
  public boolean addSki(@RequestParam(value = "itemName") String itemName) {
    return skillService.addSki(itemName);
  }

  @RequestMapping("removeSki")
  public boolean removeSki(@RequestParam(value = "chkList") List<String> chkList) {
    for (int i = 0; i < chkList.size(); i++) {
      Boolean chk = skillService.removeSki(Integer.parseInt(chkList.get(i)));
      if (!chk)
        return false;
    }
    return true;
  }

  @RequestMapping("editSki")
  public boolean editSki(@RequestParam(value = "id") int id,
      @RequestParam(value = "editItemName") String editItemName) {
    return skillService.editSki(id, editItemName);
  }

  // 고용형태
  @RequestMapping("/hir")
  public List<HireKindDto> getHir() {
    return hireKindService.getHir();
  }

  @RequestMapping("/addHir")
  public boolean addHir(@RequestParam(value = "itemName") String itemName) {
    return hireKindService.addHir(itemName);
  }

  @RequestMapping("removeHir")
  public boolean removeHir(@RequestParam(value = "chkList") List<String> chkList) {
    for (int i = 0; i < chkList.size(); i++) {
      Boolean chk = hireKindService.removeHir(Integer.parseInt(chkList.get(i)));
      if (!chk)
        return false;
    }
    return true;
  }

  @RequestMapping("editHir")
  public boolean editHir(@RequestParam(value = "id") int id,
      @RequestParam(value = "editItemName") String editItemName) {
    return hireKindService.editHir(id, editItemName);
  }

}
