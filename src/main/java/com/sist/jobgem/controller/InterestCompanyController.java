package com.sist.jobgem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.sist.jobgem.dto.InterestCompanyDto;
import com.sist.jobgem.dto.InterestCompanyResponse;
import com.sist.jobgem.service.InterestCompanyService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/interest")
public class InterestCompanyController {
    @Autowired
    private InterestCompanyService interestCompanyService;

    @PostMapping("/{coIdx}")
    public ResponseEntity<String> addInterestCompany(@PathVariable("coIdx") Integer coIdx, @RequestParam("joIdx") Integer joIdx) {
        return ResponseEntity.ok(interestCompanyService.managementInterest(coIdx, joIdx));
    }

    @GetMapping("/{coIdx}")
    public ResponseEntity<Boolean> isInterest(@PathVariable("coIdx") Integer coIdx, @RequestParam("joIdx") Integer joIdx) {
        return ResponseEntity.ok(interestCompanyService.isInterest(coIdx, joIdx));
    }

    @GetMapping("/list/{joIdx}")
    public ResponseEntity<List<InterestCompanyResponse>> getInterestCompanies(@PathVariable("joIdx") Integer joIdx) {
        return ResponseEntity.ok(interestCompanyService.getInterestCompanies(joIdx));
    }
}
