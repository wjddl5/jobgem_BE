package com.sist.jobgem.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sist.jobgem.dto.BlockDto;
import com.sist.jobgem.dto.CompanyDto;
import com.sist.jobgem.dto.InterviewDto;
import com.sist.jobgem.dto.JobseekerDto;
import com.sist.jobgem.dto.ResumeDto;
import com.sist.jobgem.dto.ReviewDto;
import com.sist.jobgem.entity.Interview;
import com.sist.jobgem.entity.Resume;
import com.sist.jobgem.entity.Review;
import com.sist.jobgem.service.BlockService;
import com.sist.jobgem.service.CompanyService;
import com.sist.jobgem.service.InterviewService;
import com.sist.jobgem.service.JobseekerService;
import com.sist.jobgem.service.ResumeService;
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

    @Autowired
    InterviewService interviewService;

    @Autowired
    ResumeService resumeService;

    @Autowired
    BlockService blockService;

    @GetMapping("/jobseeker")
    public ResponseEntity<JobseekerDto> getJobseeker(int id) {
        return ResponseEntity.ok(jobseekerService.getJobseeker(id));
    }

    @GetMapping("/reviewList")
    public Page<ReviewDto> getReviewList(int id, Pageable pageable) {
        return reviewService.getReviewList(id, pageable);
    }

    @GetMapping("/interviewList")
    public Page<InterviewDto> getInterviewist(int id, Pageable pageable) {
        return interviewService.getInterviewist(id, pageable);
    }

    @GetMapping("/resumeList")
    public Page<ResumeDto> getresumeList(int id, Pageable pageable) {
        return resumeService.getResumeList(id, pageable);
    }

    @GetMapping("/companyList")
    public List<CompanyDto> getCompanyList() {
        return reviewService.getCompanyList();
    }

    @GetMapping("/addReview")
    public Review addReview(@RequestBody ReviewDto dto) {
        return reviewService.addReview(dto);
    }

    @GetMapping("/addInterview")
    public Interview addInterview(@RequestBody InterviewDto dto) {
        return interviewService.addInterview(dto);
    }

    @GetMapping("/addResume")
    public Resume addResume(@RequestBody ResumeDto dto) {
        return resumeService.addResume(dto);
    }

    @GetMapping("/getReview")
    public ReviewDto getReview(int id) {
        return reviewService.getReview(id);
    }

    @GetMapping("/getInterview")
    public InterviewDto getInterview(int id) {
        return interviewService.getInterview(id);
    }

    @GetMapping("/getResume")
    public ResumeDto getResume(int id) {
        return resumeService.getResume(id);
    }

    @GetMapping("/updateReview")
    public Review updateReview(@RequestBody ReviewDto dto) {
        return reviewService.updateReview(dto);
    }

    @GetMapping("/updateInterview")
    public Interview updateInterview(@RequestBody InterviewDto dto) {
        return interviewService.updateInterview(dto);
    }

    @GetMapping("/updateResume")
    public Resume updateResume(@RequestBody ResumeDto dto) {
        return resumeService.updateResume(dto);
    }

    @GetMapping("/deleteReview")
    public int deleteReview(int id) {
        return reviewService.deleteReview(id);
    }

    @GetMapping("/deleteInterview")
    public int deleteInterview(int id) {
        return interviewService.deleteInterview(id);
    }

    @GetMapping("/deleteResume")
    public int deleteResume(int id) {
        return resumeService.deleteResume(id);
    }

    @GetMapping("/userlist")
    public Page<JobseekerDto> getJobseekerList(@RequestBody Pageable pageable,
            @RequestParam(required = false) String value, @RequestParam(required = false) String type) {
        return jobseekerService.getJobseekerList(pageable, value, type);
    }

    @GetMapping("/blocklist")
    public Page<BlockDto> getBlockList(@RequestBody Pageable pageable, @RequestParam(required = false) String value,
            @RequestParam(required = false) String type) {
        return blockService.getblackList(pageable, value, type);
    }
}
