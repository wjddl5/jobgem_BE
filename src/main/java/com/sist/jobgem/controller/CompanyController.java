package com.sist.jobgem.controller;

import com.sist.jobgem.dto.CompanyIndexDto;
import com.sist.jobgem.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("")
    public ResponseEntity<CompanyIndexDto> Index(int id, int blockPage) {
        return ResponseEntity.ok(companyService.getCompany(id, blockPage));
    }
}
