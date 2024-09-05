package com.sist.jobgem.controller;

import com.sist.jobgem.dto.CompanyIndexDto;
import com.sist.jobgem.dto.FitJobseekerDto;
import com.sist.jobgem.service.CompanyService;
import com.sist.jobgem.service.JobseekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private JobseekerService jobseekerService;

    @GetMapping("")
    public ResponseEntity<CompanyIndexDto> Index(int id, int blockPage) {
        return ResponseEntity.ok(companyService.getCompany(id, blockPage));
    }

    @GetMapping("/fit")
    public ResponseEntity<FitJobseekerDto> getFitCompany(int id, int loadPage) {
        PageRequest pageable = PageRequest.of(loadPage, 15, Sort.by(Sort.Direction.DESC, "joName"));
        return ResponseEntity.ok(jobseekerService.fitJobseekerList(id, pageable));
    }

}
