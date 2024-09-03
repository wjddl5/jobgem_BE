package com.sist.jobgem.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sist.jobgem.dto.CompanyDto;
import com.sist.jobgem.dto.JobseekerDto;
import com.sist.jobgem.dto.ReviewDto;
import com.sist.jobgem.entity.Review;
import com.sist.jobgem.service.CompanyService;
import com.sist.jobgem.service.JobseekerService;
import com.sist.jobgem.service.ReviewService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class JobseekerController {
    @Autowired
    JobseekerService jobseekerService;

    @Autowired
    ReviewService reviewService;

    @GetMapping("/jobseeker")
    public ResponseEntity<JobseekerDto> getJobseeker(int id) {
        return ResponseEntity.ok(jobseekerService.getJobseeker(id));
    }

    @GetMapping("/reviewList")
    public Page<ReviewDto> getReviewList(int id, Pageable pageable) {
        return reviewService.getReviewList(id, pageable);
    }

    @GetMapping("/companyList")
    public List<CompanyDto> getCompanyList() {
        return reviewService.getCompanyList();
    }

    @GetMapping("/addReview")
    public Review addReview(@RequestBody ReviewDto dto) {
        return reviewService.addReview(dto);
    }

    @GetMapping("/getReview")
    public ReviewDto getReview(int id) {
        return reviewService.getReview(id);
    }

    @GetMapping("/updateReview")
    public Review updateReview(@RequestBody ReviewDto dto) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(dto);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        return reviewService.updateReview(dto);
    }

}
