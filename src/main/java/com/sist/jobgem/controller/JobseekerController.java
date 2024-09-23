package com.sist.jobgem.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sist.jobgem.dto.ApplymentDto;
import com.sist.jobgem.dto.ApplymentSearchDto;
import com.sist.jobgem.dto.BlockDto;
import com.sist.jobgem.dto.CompanyDto;
import com.sist.jobgem.dto.InterviewDto;
import com.sist.jobgem.dto.JobseekerDto;
import com.sist.jobgem.dto.OfferDto;
import com.sist.jobgem.dto.PostDto;
import com.sist.jobgem.dto.ResumeDto;
import com.sist.jobgem.dto.ReviewDto;
import com.sist.jobgem.dto.SkillDto;
import com.sist.jobgem.dto.UserDto;
import com.sist.jobgem.entity.Applyment;
import com.sist.jobgem.entity.Interview;

import com.sist.jobgem.entity.Jobseeker;

import com.sist.jobgem.entity.Resume;
import com.sist.jobgem.entity.Review;
import com.sist.jobgem.mapper.UserMapper;
import com.sist.jobgem.service.ApplymentService;
import com.sist.jobgem.service.BlockService;
import com.sist.jobgem.service.CompanyService;
import com.sist.jobgem.service.InterviewService;
import com.sist.jobgem.service.JobseekerService;
import com.sist.jobgem.service.OfferService;
import com.sist.jobgem.service.PostService;
import com.sist.jobgem.service.ResumeService;
import com.sist.jobgem.service.ReviewService;
import com.sist.jobgem.service.SkillService;
import com.sist.jobgem.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class JobseekerController {
    @Autowired
    UserService userService;

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

    @Autowired
    SkillService skillService;

    @Autowired
    OfferService offerService;

    @Autowired
    PostService postService;

    @Autowired
    ApplymentService applymentService;

    // jobseeker 정보 불러오기
    @GetMapping("/jobseeker")
    public ResponseEntity<JobseekerDto> getJobseeker(@RequestParam("id") int id) {
        return ResponseEntity.ok(jobseekerService.getJobseeker(id));
    }

    // 회사후기 목록 불러오기
    @GetMapping("/reviews")
    public ResponseEntity<Page<ReviewDto>> getReviewList(@RequestParam("id") int id,
            @RequestParam("curPage") int curPage) {
        PageRequest pageable = PageRequest.of(curPage, 5, Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(reviewService.getReviewList(id, pageable));
    }

    // 면접후기 목록 불러오기
    @GetMapping("/interviews")
    public ResponseEntity<Page<InterviewDto>> getInterviewist(@RequestParam("id") int id,
            @RequestParam("curPage") int curPage) {
        PageRequest pageable = PageRequest.of(curPage, 5, Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(interviewService.getInterviewist(id, pageable));
    }

    // 이력서 목록 불러오기
    @GetMapping("/resumes")
    public ResponseEntity<Page<ResumeDto>> getresumeList(@RequestParam("id") int id,
            @RequestParam("curPage") int curPage) {
        PageRequest pageable = PageRequest.of(curPage, 5, Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(resumeService.getResumeList(id, pageable));
    }

    // 회사지원 목록 불러오기
    @GetMapping("/applyments")
    public ResponseEntity<Page<ApplymentDto>> getApplymentList(@RequestParam("id") int id,
            @RequestParam("curPage") int curPage) {
        PageRequest pageable = PageRequest.of(curPage, 5,
                Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(applymentService.getApplymentList(id, pageable));
    }

    // 회사지원 - 검색 목록 불러오기
    @GetMapping("/search/applyment")
    public Page<ApplymentDto> getApplymentListByFilters(@ModelAttribute ApplymentSearchDto dto,
            @RequestParam(value = "curPage", required = false) int curPage) {
        PageRequest pageable = PageRequest.of(curPage, 5, Sort.by(Sort.Direction.DESC, "id"));
        return applymentService.searchApplyment(dto, pageable);
    }

    // 마이페이지 이력서, 지원현황 등 개수 정보 불러오기
    @GetMapping("/mypage/count")
    public ResponseEntity<Map<String, Object>> getMypageCount(@RequestParam("id") int id) {
        return ResponseEntity.ok(jobseekerService.getMypageCount(id));
    }

    // 지원 개수 불러오기
    @GetMapping("/applyment/count")
    public ResponseEntity<Map<String, Object>> getApplymentCount(@RequestParam("id") int id) {
        return ResponseEntity.ok(applymentService.applymentCount(id));
    }

    // 입사제안 목록 불러오기
    @GetMapping("/offers")
    public ResponseEntity<Page<OfferDto>> getOfferList(@RequestParam("id") int id,
            @RequestParam("curPage") int curPage) {
        PageRequest pageable = PageRequest.of(curPage, 5, Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(offerService.getOfferList(id, pageable));
    }

    // 공고목록 불러오기
    @GetMapping("/posts/main")
    public ResponseEntity<Slice<PostDto>> getPostList(@RequestParam("loadPage") int loadPage) {
        PageRequest pageable = PageRequest.of(loadPage, 15, Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(postService.getPostList(pageable));
    }

    // 회사목록 불러오기
    @GetMapping("/companies")
    public ResponseEntity<List<CompanyDto>> getCompanyList() {
        return ResponseEntity.ok(reviewService.getCompanyList());
    }

    // 기술 목록 불러오기
    @GetMapping("/skills")
    public ResponseEntity<List<SkillDto>> getSkillList() {
        return ResponseEntity.ok(skillService.getSkillList());
    }

    // 회사 후기 추가하기
    @PostMapping("/review")
    public ResponseEntity<Review> addReview(@RequestBody ReviewDto dto) {
        ReviewDto rto = new ReviewDto(dto);
        return ResponseEntity.ok(reviewService.addReview(rto));
    }

    // 면접 후기 추가하기
    @PostMapping("/interview")
    public ResponseEntity<Interview> addInterview(@RequestBody InterviewDto dto) {
        InterviewDto ito = new InterviewDto(dto);
        return ResponseEntity.ok(interviewService.addInterview(ito));
    }

    // 이력서 추가하기
    @PostMapping("/resume")
    public ResponseEntity<Resume> addResume(@RequestBody ResumeDto dto) {
        ResumeDto rto = new ResumeDto(dto);
        return ResponseEntity.ok(resumeService.addResume(rto));
    }

    // 공고지원 추가하기
    @PostMapping("/applyment")
    public ResponseEntity<Applyment> addApplyment(@RequestBody ApplymentDto applymentDto,
            @RequestParam("joIdx") int joIdx) {
        Applyment applyment = applymentService.addApplyment(applymentDto, joIdx);
        return ResponseEntity.ok(applyment);
    }

    // 회사후기 상세정보 불러오기
    @GetMapping("/review")
    public ResponseEntity<ReviewDto> getReview(@RequestParam("id") int id) {
        return ResponseEntity.ok(reviewService.getReview(id));
    }

    // 면접후기 상세정보 불러오기
    @GetMapping("/interview")
    public ResponseEntity<InterviewDto> getInterview(@RequestParam("id") int id) {
        return ResponseEntity.ok(interviewService.getInterview(id));
    }

    // 이력서 상세정보 불러오기
    @GetMapping("/resume")
    public ResponseEntity<ResumeDto> getResume(@RequestParam("id") int id) {
        return ResponseEntity.ok(resumeService.getResume(id));
    }

    // 회사후기 수정하기
    @PutMapping("/review")
    public ResponseEntity<Review> updateReview(@RequestBody ReviewDto dto) {
        return ResponseEntity.ok(reviewService.updateReview(dto));
    }

    // 면접후기 수정하기
    @PutMapping("/interview")
    public ResponseEntity<Interview> updateInterview(@RequestBody InterviewDto dto) {
        return ResponseEntity.ok(interviewService.updateInterview(dto));
    }

    // 이력서 수정하기
    @PutMapping("/resume")
    public ResponseEntity<Resume> updateResume(@RequestBody ResumeDto dto) {
        return ResponseEntity.ok(resumeService.updateResume(dto));
    }

    // 마이페이지 수정하기
    @PutMapping("/mypage")
    public ResponseEntity<Jobseeker> updateJobseekerDetails(@RequestParam("id") int id,
            @RequestBody JobseekerDto jobseekerDto) {
        return ResponseEntity.ok(jobseekerService.updateJobseekerDetails(id, jobseekerDto));
    }

    // 대표이력서 설정하기
    @PutMapping("/resume/default")
    public ResponseEntity<String> updateDefaultResume(@RequestParam("id") int resumeId,
            @RequestParam("joIdx") int joIdx) {
        try {
            resumeService.updateDefaultResume(resumeId, joIdx); // 서비스에서 대표 이력서 업데이트 로직 실행
            return ResponseEntity.ok("1");
        } catch (Exception e) {
            return ResponseEntity.ok("0");
        }
    }

    // 회사후기 삭제하기
    @DeleteMapping("/review")
    public ResponseEntity<Integer> deleteReview(@RequestParam("id") int id) {
        return ResponseEntity.ok(reviewService.deleteReview(id));
    }

    // 면접후기 삭제하기
    @DeleteMapping("/interview")
    public ResponseEntity<Integer> deleteInterview(@RequestParam("id") int id) {
        return ResponseEntity.ok(interviewService.deleteInterview(id));
    }

    // 이력서 삭제하기
    @DeleteMapping("/resume")
    public ResponseEntity<Integer> deleteResume(@RequestParam("id") int id) {
        return ResponseEntity.ok(resumeService.deleteResume(id));
    }

    // 비밀번호 확인하기
    @GetMapping("/password/check")
    public ResponseEntity<String> checkPwd(@RequestParam("id") int id, @RequestParam("usPw") String chkPw) {
        boolean isPwdCorrect = jobseekerService.checkPwd(id, chkPw);
        if (isPwdCorrect) {
            return ResponseEntity.ok("1");
        } else {
            return ResponseEntity.ok("0");
        }
    }

    @PutMapping("/password")
    public ResponseEntity<String> updatePassword(@RequestParam("id") int id, @RequestParam("newPwd") String newPwd) {
        boolean isUpdated = jobseekerService.updatePassword(id, newPwd);
        if (isUpdated) {
            return ResponseEntity.ok("1"); // 비밀번호 변경 성공
        } else {
            return ResponseEntity.ok("0"); // 비밀번호 변경 실패
        }
    }

    // 회원탈퇴
    @DeleteMapping("/account")
    public ResponseEntity<String> deleteUser(@RequestParam("id") int id) {
        boolean isDeleted = jobseekerService.deleteUser(id);

        if (isDeleted) {
            return ResponseEntity.ok("1"); // 성공적으로 삭제된 경우 "1" 반환
        } else {
            return ResponseEntity.ok("0"); // 실패 시 "0" 반환
        }
    }

}
