package com.sist.jobgem.controller;

import com.sist.jobgem.dto.*;
import com.sist.jobgem.service.CompanyService;

import com.sist.jobgem.service.TalentService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private TalentService talentService;

    @GetMapping("")
    public ResponseEntity<CompanyIndexDto> Index(@RequestParam int id,@RequestParam int blockPage) {
        PageRequest pageable = PageRequest.of(blockPage, 2, Sort.by(Sort.Direction.DESC, "blDate"));
        return ResponseEntity.ok(companyService.getCompany(id, pageable));
    }

    @GetMapping("/fit")
    public ResponseEntity<FitJobseekerDto> getFitCompany(int id, int loadPage) {
        PageRequest pageable = PageRequest.of(loadPage, 15, Sort.by(Sort.Direction.DESC, "joName"));
        return ResponseEntity.ok(companyService.getFitJobseekerList(id, pageable));
    }

    @GetMapping("/wish")
    public ResponseEntity<TalentResponseDto> getWishJobseekerList(@RequestParam int id, @RequestParam int loadPage) {
        PageRequest pageable = PageRequest.of(loadPage, 15, Sort.by(Sort.Direction.DESC, "joIdx"));
        return ResponseEntity.ok(companyService.getWishjobseekerList(id, pageable));
    }

    @PostMapping("/wish/add")
    public ResponseEntity<Integer> addWishJobseeker(@RequestBody TalentDto request) {
        return ResponseEntity.ok(talentService.addTalent(request));
    }

    @PostMapping("/wish/remove")
    public void removeWishJobseeker(@RequestBody int id) {
        talentService.removeTalent(id);
    }

    @GetMapping("/list")
    public Page<CompanyDto> getCompanyList(@RequestBody Pageable pageable, @RequestParam(required = false) String value, @RequestParam(required = false) String type) {
        return companyService.getCompanyList(pageable, value, type);
    }
}
