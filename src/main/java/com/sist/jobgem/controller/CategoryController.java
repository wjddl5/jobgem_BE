package com.sist.jobgem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sist.jobgem.dto.AllCategoryResponse;
import com.sist.jobgem.dto.CareerDto;
import com.sist.jobgem.dto.EducationDto;
import com.sist.jobgem.dto.HireKindDto;
import com.sist.jobgem.dto.LocationDoDto;
import com.sist.jobgem.dto.LocationGuSiDto;
import com.sist.jobgem.dto.SkillDto;
import com.sist.jobgem.service.CareerService;
import com.sist.jobgem.service.CategoryService;
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

  @Autowired
  private CategoryService categoryService;

  // 학력
  @GetMapping("/edu")
  public ResponseEntity<List<EducationDto>> getEdu() {
    return ResponseEntity.ok(educationService.getEdu());
  }

  @PostMapping("/edu")
  public boolean addEdu(@RequestParam(value = "itemName") String itemName) {
    return educationService.addEdu(itemName);
  }

  @DeleteMapping("/edu")
  public boolean deleteEdu(@RequestParam(value = "chkList") List<String> chkList) {
    for (int i = 0; i < chkList.size(); i++) {
      Boolean chk = educationService.deleteEdu(Integer.parseInt(chkList.get(i)));
      if (!chk)
        return false;
    }
    return true;
  }

  @PutMapping("/edu/{id}")
  public boolean updateEdu(@PathVariable int id,
      @RequestParam(value = "editItemName") String editItemName) {
    return educationService.updateEdu(id, editItemName);
  }

  // 경력
  @GetMapping("/car")
  public ResponseEntity<List<CareerDto>> getCar() {
    return ResponseEntity.ok(careerService.getCar());
  }

  @PostMapping("/car")
  public boolean addCar(@RequestParam(value = "itemName") String itemName) {
    return careerService.addCar(itemName);
  }

  @DeleteMapping("/car")
  public boolean deleteCar(@RequestParam(value = "chkList") List<String> chkList) {
    for (int i = 0; i < chkList.size(); i++) {
      Boolean chk = careerService.deleteCar(Integer.parseInt(chkList.get(i)));
      if (!chk)
        return false;
    }
    return true;
  }

  @PutMapping("/car/{id}")
  public boolean updateCar(@PathVariable int id,
      @RequestParam(value = "editItemName") String editItemName) {
    return careerService.updateCar(id, editItemName);
  }

  // 기술
  @GetMapping("/ski")
  public ResponseEntity<List<SkillDto>> getSki() {
    return ResponseEntity.ok(skillService.getSki());
  }

  @PostMapping("/ski")
  public boolean addSki(@RequestParam(value = "itemName") String itemName) {
    return skillService.addSki(itemName);
  }

  @DeleteMapping("/ski")
  public boolean deleteSki(@RequestParam(value = "chkList") List<String> chkList) {
    for (int i = 0; i < chkList.size(); i++) {
      Boolean chk = skillService.deleteSki(Integer.parseInt(chkList.get(i)));
      if (!chk)
        return false;
    }
    return true;
  }

  @PutMapping("/ski/{id}")
  public boolean updateSki(@PathVariable int id,
      @RequestParam(value = "editItemName") String editItemName) {
    return skillService.updateSki(id, editItemName);
  }

  // 고용형태
  @GetMapping("/hir")
  public ResponseEntity<List<HireKindDto>> getHir() {
    return ResponseEntity.ok(hireKindService.getHir());
  }

  @PostMapping("/hir")
  public boolean addHir(@RequestParam(value = "itemName") String itemName) {
    return hireKindService.addHir(itemName);
  }

  @DeleteMapping("/hir")
  public boolean deleteHir(@RequestParam(value = "chkList") List<String> chkList) {
    for (int i = 0; i < chkList.size(); i++) {
      Boolean chk = hireKindService.deleteHir(Integer.parseInt(chkList.get(i)));
      if (!chk)
        return false;
    }
    return true;
  }

  @PutMapping("/hir/{id}")
  public boolean updateHir(@PathVariable int id,
      @RequestParam(value = "editItemName") String editItemName) {
    return hireKindService.updateHir(id, editItemName);
  }

  // 지역 (도)
  @GetMapping("/loc")
  public ResponseEntity<List<LocationDoDto>> getLoc() {
    return ResponseEntity.ok(locationDoService.getLoc());
  }

  @PostMapping("/loc")
  public boolean addLoc(@RequestParam(value = "itemName") String itemName) {
    return locationDoService.addLoc(itemName);
  }

  @DeleteMapping("/loc/{id}")
  public boolean deleteLoc(@PathVariable int id) {
    return locationDoService.deleteLoc(id);
  }

  @PutMapping("/loc/{id}")
  public boolean updateLoc(@PathVariable int id,
      @RequestParam(value = "editDoName") String editDoName) {
    return locationDoService.updateLoc(id, editDoName);
  }

  // 지역 (구, 시)
  @GetMapping("/locGuSi/{ldIdx}")
  public ResponseEntity<List<LocationGuSiDto>> getLocGuSi(@PathVariable int ldIdx) {
    return ResponseEntity.ok(locationGuSiService.getLocGuSi(ldIdx));
  }

  @PostMapping("/locGuSi/{ldIdx}")
  public boolean addLocGuSi(@PathVariable int ldIdx,
      @RequestParam(value = "itemName") String itemName) {
    return locationGuSiService.addLocGuSi(itemName, ldIdx);
  }

  @DeleteMapping("/locGuSi")
  public boolean deleteLocGuSi(@RequestParam(value = "chkList") List<String> chkList) {
    for (int i = 0; i < chkList.size(); i++) {
      Boolean chk = locationGuSiService.deleteLocGuSi(Integer.parseInt(chkList.get(i)));
      if (!chk)
        return false;
    }
    return true;
  }

  @PutMapping("/locGuSi/{id}")
  public boolean updateLocGuSi(@PathVariable int id,
      @RequestParam(value = "editItemName") String editItemName) {
    return locationGuSiService.updateLocGuSi(id, editItemName);
  }

  @GetMapping("/all")
  public ResponseEntity<AllCategoryResponse> getAllCategory() {
    return ResponseEntity.ok(categoryService.getAll());
  }

}
