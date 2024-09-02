package com.sist.jobgem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sist.jobgem.dto.JobseekerDto;
import com.sist.jobgem.dto.ReviewDto;
import com.sist.jobgem.entity.Review;
import com.sist.jobgem.service.JobseekerService;
import com.sist.jobgem.service.ReviewService;

import org.springframework.web.bind.annotation.GetMapping;
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

}
