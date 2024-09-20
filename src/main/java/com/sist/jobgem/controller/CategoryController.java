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

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  @GetMapping("/edu")
  public List<EducationDto> getEdu() {
    return educationService.getEdu();
  }

  @PostMapping("/edu")
  public boolean addEdu(@RequestParam(value = "itemName") String itemName) {
    return educationService.addEdu(itemName);
  }

  @DeleteMapping("/edu")
  public boolean removeEdu(@RequestParam(value = "chkList") List<String> chkList) {
    for (int i = 0; i < chkList.size(); i++) {
      Boolean chk = educationService.removeEdu(Integer.parseInt(chkList.get(i)));
      if (!chk)
        return false;
    }
    return true;
  }

  @PutMapping("/edu/{id}")
  public boolean editEdu(@PathVariable int id,
      @RequestParam(value = "editItemName") String editItemName) {
    return educationService.editEdu(id, editItemName);
  }

  // 경력
  @GetMapping("/car")
  public List<CareerDto> getCar() {
    return careerService.getCar();
  }

  @PostMapping("/car")
  public boolean addCar(@RequestParam(value = "itemName") String itemName) {
    return careerService.addCar(itemName);
  }

  @DeleteMapping("/car")
  public boolean removeCar(@RequestParam(value = "chkList") List<String> chkList) {
    for (int i = 0; i < chkList.size(); i++) {
      Boolean chk = careerService.removeCar(Integer.parseInt(chkList.get(i)));
      if (!chk)
        return false;
    }
    return true;
  }

  @PutMapping("/car/{id}")
  public boolean editCar(@PathVariable int id,
      @RequestParam(value = "editItemName") String editItemName) {
    return careerService.editCar(id, editItemName);
  }

  // 기술
  @GetMapping("/ski")
  public List<SkillDto> getSki() {
    return skillService.getSki();
  }

  @PostMapping("/ski")
  public boolean addSki(@RequestParam(value = "itemName") String itemName) {
    return skillService.addSki(itemName);
  }

  @DeleteMapping("/ski")
  public boolean removeSki(@RequestParam(value = "chkList") List<String> chkList) {
    for (int i = 0; i < chkList.size(); i++) {
      Boolean chk = skillService.removeSki(Integer.parseInt(chkList.get(i)));
      if (!chk)
        return false;
    }
    return true;
  }

  @PutMapping("/ski/{id}")
  public boolean editSki(@PathVariable int id,
      @RequestParam(value = "editItemName") String editItemName) {
    return skillService.editSki(id, editItemName);
  }

  // 고용형태
  @GetMapping("/hir")
  public List<HireKindDto> getHir() {
    return hireKindService.getHir();
  }

  @PostMapping("/hir")
  public boolean addHir(@RequestParam(value = "itemName") String itemName) {
    return hireKindService.addHir(itemName);
  }

  @DeleteMapping("/hir")
  public boolean removeHir(@RequestParam(value = "chkList") List<String> chkList) {
    for (int i = 0; i < chkList.size(); i++) {
      Boolean chk = hireKindService.removeHir(Integer.parseInt(chkList.get(i)));
      if (!chk)
        return false;
    }
    return true;
  }

  @PutMapping("/hir/{id}")
  public boolean editHir(@PathVariable int id,
      @RequestParam(value = "editItemName") String editItemName) {
    return hireKindService.editHir(id, editItemName);
  }

  // 지역 (도)
  @GetMapping("/loc")
  public List<LocationDoDto> getLoc() {
    return locationDoService.getLoc();
  }

  @PostMapping("/loc")
  public boolean addLoc(@RequestParam(value = "itemName") String itemName) {
    return locationDoService.addLoc(itemName);
  }

  @DeleteMapping("/loc/{id}")
  public boolean removeLoc(@PathVariable int id) {
    return locationDoService.removeLoc(id);
  }

  @PutMapping("/loc/{id}")
  public boolean editLoc(@PathVariable int id,
      @RequestParam(value = "editDoName") String editDoName) {
    return locationDoService.editLoc(id, editDoName);
  }

  // 지역 (구, 시)
  @GetMapping("/locGuSi/{ldIdx}")
  public List<LocationGuSiDto> getLocGuSi(@PathVariable int ldIdx) {
    return locationGuSiService.getLocGuSi(ldIdx);
  }

  @PostMapping("/locGuSi/{ldIdx}")
  public boolean addLocGuSi(@PathVariable int ldIdx,
      @RequestParam(value = "itemName") String itemName) {
    return locationGuSiService.addLocGuSi(itemName, ldIdx);
  }

  @DeleteMapping("/locGuSi")
  public boolean removeLocGuSi(@RequestParam(value = "chkList") List<String> chkList) {
    for (int i = 0; i < chkList.size(); i++) {
      Boolean chk = locationGuSiService.removeLocGuSi(Integer.parseInt(chkList.get(i)));
      if (!chk)
        return false;
    }
    return true;
  }

  @PutMapping("/locGuSi/{id}")
  public boolean editLocGuSi(@PathVariable int id,
      @RequestParam(value = "editItemName") String editItemName) {
    return locationGuSiService.editLocGuSi(id, editItemName);
  }

}
