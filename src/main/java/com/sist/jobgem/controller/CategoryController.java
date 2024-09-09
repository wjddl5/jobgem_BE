package com.sist.jobgem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sist.jobgem.dto.CareerDto;
import com.sist.jobgem.dto.EducationDto;
import com.sist.jobgem.dto.HireKindDto;
import com.sist.jobgem.dto.LocationDoDto;
import com.sist.jobgem.dto.LocationGuSiDto;
import com.sist.jobgem.dto.SkillDto;
import com.sist.jobgem.service.CareerService;
import com.sist.jobgem.service.EducationService;
import com.sist.jobgem.service.HireKindService;
import com.sist.jobgem.service.LocationDoService;
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
  private LocationDoService locationDoService;

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

  // 지역 (도)
  @RequestMapping("/loc")
  public List<LocationDoDto> getLoc() {
    return locationDoService.getLoc();
  }

  @RequestMapping("/addLoc")
  public boolean addLoc(@RequestParam(value = "itemName") String itemName) {
    return locationDoService.addLoc(itemName);
  }

  @RequestMapping("removeLoc")
  public boolean removeLoc(@RequestParam(value = "id") int id) {
    return locationDoService.removeLoc(id);
  }

  @RequestMapping("editLoc")
  public boolean editLoc(@RequestParam(value = "id") int id,
      @RequestParam(value = "editDoName") String editDoName) {
    return locationDoService.editLoc(id, editDoName);
  }

  // 지역 (구, 시)
  @RequestMapping("/locGuSi")
  public List<LocationGuSiDto> getLocGuSi(@RequestParam(value = "ldIdx") int ldIdx) {
    return locationGuSiService.getLocGuSi(ldIdx);
  }

  @RequestMapping("/addLocGuSi")
  public boolean addLocGuSi(@RequestParam(value = "itemName") String itemName,
      @RequestParam(value = "ldIdx") int ldIdx) {
    return locationGuSiService.addLocGuSi(itemName, ldIdx);
  }

  @RequestMapping("removeLocGuSi")
  public boolean removeLocGuSi(@RequestParam(value = "chkList") List<String> chkList) {
    for (int i = 0; i < chkList.size(); i++) {
      Boolean chk = locationGuSiService.removeLocGuSi(Integer.parseInt(chkList.get(i)));
      if (!chk)
        return false;
    }
    return true;
  }

  @RequestMapping("editLocGuSi")
  public boolean editLocGuSi(@RequestParam(value = "id") int id,
      @RequestParam(value = "editItemName") String editItemName) {
    return locationGuSiService.editLocGuSi(id, editItemName);
  }

}
