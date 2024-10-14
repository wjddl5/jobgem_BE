package com.sist.jobgem.controller;

import java.util.List;
import java.util.Map;

import com.sist.jobgem.enums.AlertMessageEnum;
import com.sist.jobgem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.jobgem.dto.ApplymentDto;
import com.sist.jobgem.dto.ApplymentSearchDto;
import com.sist.jobgem.dto.CompanyDto;
import com.sist.jobgem.dto.InterviewDto;
import com.sist.jobgem.dto.JobseekerDto;
import com.sist.jobgem.dto.OfferDto;
import com.sist.jobgem.dto.PostDto;
import com.sist.jobgem.dto.ResumeDto;
import com.sist.jobgem.dto.ReviewDto;
import com.sist.jobgem.dto.SkillDto;
import com.sist.jobgem.entity.Applyment;
import com.sist.jobgem.entity.Interview;
import com.sist.jobgem.entity.Jobseeker;
import com.sist.jobgem.entity.Offer;
import com.sist.jobgem.entity.Resume;
import com.sist.jobgem.entity.Review;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Tag(name = "Jobseeker", description = "구직자 API")
@RestController
@RequestMapping("/api/jobseeker")
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
    SkillService skillService;

    @Autowired
    OfferService offerService;

    @Autowired
    PostService postService;

    @Autowired
    ApplymentService applymentService;
    @Autowired
    private AlertService alertService;
    @Autowired
    private SseService sseService;

    @Operation(summary = "jobseeker 정보 불러오기", description = "ID값으로 jobseeker 정보 불러오기")
    @GetMapping("/{id}")
    public ResponseEntity<JobseekerDto> getJobseeker(@PathVariable("id") int id) {
        return ResponseEntity.ok(jobseekerService.getJobseeker(id));
    }

    @Operation(summary = "회사후기 목록 불러오기", description = "ID값으로 회사후기 목록 불러오기")
    @GetMapping("/reviews/{id}")
    public ResponseEntity<Page<ReviewDto>> getReviewList(@PathVariable("id") int id,
            @RequestParam("curPage") int curPage) {
        PageRequest pageable = PageRequest.of(curPage, 5, Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(reviewService.getReviewList(id, pageable));
    }

    @Operation(summary = "면접후기 목록 불러오기", description = "ID값으로 면접후기 목록 불러오기")
    @GetMapping("/interviews/{id}")
    public ResponseEntity<Page<InterviewDto>> getInterviewist(@PathVariable("id") int id,
            @RequestParam("curPage") int curPage) {
        PageRequest pageable = PageRequest.of(curPage, 5, Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(interviewService.getInterviewist(id, pageable));
    }

    @Operation(summary = "이력서 목록 불러오기", description = "ID값으로 이력서 목록 불러오기")
    @GetMapping("/resumes/{id}")
    public ResponseEntity<Page<ResumeDto>> getresumeList(@PathVariable("id") int id,
            @RequestParam("curPage") int curPage) {
        PageRequest pageable = PageRequest.of(curPage, 5, Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(resumeService.getResumeList(id, pageable));
    }

    @Operation(summary = "회사지원 목록 불러오기", description = "ID값으로 회사지원 목록 불러오기")
    @GetMapping("/applyments/{id}")
    public ResponseEntity<Page<ApplymentDto>> getApplymentList(@PathVariable("id") int id,
            @RequestParam("curPage") int curPage) {
        PageRequest pageable = PageRequest.of(curPage, 5,
                Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(applymentService.getApplymentList(id, pageable));
    }

    @Operation(summary = "회사지원 - 검색조건에 맞는 목록 불러오기", description = "ApplymentSearchDto를 받아 ID값으로 검색조건에 맞는 회사지원 목록 불러오기")
    @GetMapping("/search/applyment")
    public ResponseEntity<Page<ApplymentDto>> getApplymentListByFilters(@RequestBody ApplymentSearchDto dto,
            @RequestParam(value = "curPage", required = false) int curPage) {
        PageRequest pageable = PageRequest.of(curPage, 5, Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(applymentService.searchApplyment(dto, pageable));
    }

    @Operation(summary = "마이페이지에서 데이터 개수 불러오기", description = "ID값으로 마이페이지에서 데이터 개수 불러오기")
    @GetMapping("/mypage/count/{id}")
    public ResponseEntity<Map<String, Object>> getMypageCount(@PathVariable("id") int id) {
        return ResponseEntity.ok(jobseekerService.getMypageCount(id));
    }

    @Operation(summary = "입사지원페이지에서 데이터 개수 불러오기", description = "ID값으로 입사지원페이지에서 데이터 개수 불러오기")
    @GetMapping("/applyment/count/{id}")
    public ResponseEntity<Map<String, Object>> getApplymentCount(@PathVariable("id") int id) {
        return ResponseEntity.ok(applymentService.applymentCount(id));
    }

    @Operation(summary = "입사제안 목록 불러오기", description = "ID값으로 입사제안목록 불러오기")
    @GetMapping("/offers/{id}")
    public ResponseEntity<Page<OfferDto>> getOfferList(@PathVariable("id") int id,
            @RequestParam("curPage") int curPage) {
        PageRequest pageable = PageRequest.of(curPage, 5, Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(offerService.getOfferList(id, pageable));
    }

    @Operation(summary = "입사지원 거절하기", description = "지원 ID를 사용하여 특정 입사 지원을 거절합니다")
    @PutMapping("/offers/{offerId}/reject")
    public ResponseEntity<Offer> rejectOffer(@PathVariable("offerId") int offerId) {
        return ResponseEntity.ok(offerService.rejectOffer(offerId));
    }

    @Operation(summary = "공고 목록 불러오기", description = "메인페이지 - 무한스크롤 공고목록 불러오기")
    @GetMapping("/posts/main")
    public ResponseEntity<Slice<PostDto>> getPostList(@RequestParam("loadPage") int loadPage) {
        PageRequest pageable = PageRequest.of(loadPage, 15, Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(postService.getPostList(pageable));
    }

    @Operation(summary = "회사 목록 불러오기", description = "후기페이지들에서 사용 할 회사목록 불러오기")
    @GetMapping("/companies")
    public ResponseEntity<List<CompanyDto>> getCompanyList() {
        return ResponseEntity.ok(reviewService.getCompanyList());
    }

    @Operation(summary = "기술 목록 불러오기", description = "마이페이지 수정 시 사용 할 기술목록 불러오기")
    @GetMapping("/skills")
    public ResponseEntity<List<SkillDto>> getSkillList() {
        return ResponseEntity.ok(skillService.getSki());
    }

    @Operation(summary = "회사 후기 작성하기", description = "회사 후기 작성하기")
    @PostMapping("/review")
    public ResponseEntity<Review> addReview(@RequestBody ReviewDto dto) {
        ReviewDto rto = new ReviewDto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.addReview(rto));
    }

    @Operation(summary = "면접 후기 작성하기", description = "면접 후기 작성하기")
    @PostMapping("/interview")
    public ResponseEntity<Interview> addInterview(@RequestBody InterviewDto dto) {
        InterviewDto ito = new InterviewDto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(interviewService.addInterview(ito));
    }

    @Operation(summary = "이력서 작성하기", description = "이력서 작성하기")
    @PostMapping("/resume")
    public ResponseEntity<Resume> addResume(@RequestBody ResumeDto dto) {
        ResumeDto resumeDto = new ResumeDto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resumeService.addResume(resumeDto));
    }

    @Operation(summary = "회사 공고 지원하기", description = "DTO에 있는 ID값으로 대표이력서를 구한 후 회사 공고 지원하기")
    @PostMapping("/applyment")
    public ResponseEntity<?> addApplyment(@RequestBody ApplymentDto dto) {
        // 이미 지원한 공고인지 확인
        if (applymentService.isAlreadyApplied(dto)) {
            return ResponseEntity.badRequest().body("이미 지원한 공고입니다.");
        }

        // 대표 이력서가 있는지 확인
        boolean hasDefaultResume = resumeService.hasDefaultResume(dto.getJoIdx());
        if (!hasDefaultResume) {
            return ResponseEntity.badRequest().body("대표 이력서가 없습니다.");
        }

        // 지원하기 로직 실행
        Applyment applyment = applymentService.addApplyment(dto);
        alertService.addAlert(postService.getCompanyByPoIdx(applyment.getPoIdx()).getUsIdx(), AlertMessageEnum.ALERT_APPLY.getMessage());
        sseService.sendToClient(postService.getCompanyByPoIdx(applyment.getPoIdx()).getUsIdx(), AlertMessageEnum.ALERT_APPLY.getMessage());
        return ResponseEntity.status(HttpStatus.CREATED).body(applyment);
    }

    @Operation(summary = "회사후기 조회", description = "ID값으로 회사후기 페이지 데이터 불러오기")
    @GetMapping("/review/{id}")
    public ResponseEntity<ReviewDto> getReview(@PathVariable("id") int id) {
        return ResponseEntity.ok(reviewService.getReview(id));
    }

    @Operation(summary = "면접후기 조회", description = "ID값으로 면접후기 페이지 데이터 불러오기")
    @GetMapping("/interview/{id}")
    public ResponseEntity<InterviewDto> getInterview(@PathVariable("id") int id) {
        return ResponseEntity.ok(interviewService.getInterview(id));
    }

    @Operation(summary = "이력서 ", description = "ID값으로 이력서 데이터 불러오기")
    @GetMapping("/resume/{id}")
    public ResponseEntity<ResumeDto> getResume(@PathVariable("id") int id) {
        return ResponseEntity.ok(resumeService.getResume(id));
    }

    @Operation(summary = "회사후기 수정 ", description = "DTO에 있는 ID값으로 회사후기 수정하기")
    @PutMapping("/review")
    public ResponseEntity<Review> updateReview(@RequestBody ReviewDto dto) {
        return ResponseEntity.ok(reviewService.updateReview(dto));
    }

    @Operation(summary = "면접후기 수정 ", description = "DTO에 있는 ID값으로 면접후기 수정하기")
    @PutMapping("/interview")
    public ResponseEntity<Interview> updateInterview(@RequestBody InterviewDto dto) {
        return ResponseEntity.ok(interviewService.updateInterview(dto));
    }

    @Operation(summary = "이력서 수정 ", description = "DTO에 있는 ID값으로 이력서 수정하기")
    @PutMapping("/resume")
    public ResponseEntity<Resume> updateResume(@RequestBody ResumeDto dto) {
        return ResponseEntity.ok(resumeService.updateResume(dto));
    }

    @Operation(summary = "마이페이지 수정 ", description = "ID값으로 마이페이지 수정하기")
    @PutMapping("/mypage/{id}")
    public ResponseEntity<Jobseeker> updateJobseekerDetails(@PathVariable("id") int id,
            @RequestBody JobseekerDto jobseekerDto) {
        return ResponseEntity.ok(jobseekerService.updateJobseekerDetails(id, jobseekerDto));
    }

    @Operation(summary = "대표이력서 설정 ", description = "joIdx가 갖고있는 이력서의 ID값으로 대표이력서 설정하기")
    @PutMapping("/resume/default/{resumeId}")
    public ResponseEntity<?> updateDefaultResume(@PathVariable("resumeId") int resumeId,
            @RequestParam("joIdx") int joIdx) {
        try {
            resumeService.changeDefaultResume(resumeId, joIdx);
            return ResponseEntity.ok("change default resume success");
        } catch (Exception e) {
            System.out.println("확인!" + resumeId + joIdx);
            return ResponseEntity.badRequest().body("change default resume fail");
        }
    }

    @Operation(summary = "회사후기 삭제 ", description = "ID값으로 회사후기 삭제하기")
    @DeleteMapping("/review/{id}")
    public ResponseEntity<Integer> deleteReview(@PathVariable("id") int id) {
        return ResponseEntity.ok(reviewService.deleteReview(id));
    }

    @Operation(summary = "면접후기 삭제 ", description = "ID값으로 면접후기 삭제하기")
    @DeleteMapping("/interview/{id}")
    public ResponseEntity<Integer> deleteInterview(@PathVariable("id") int id) {
        return ResponseEntity.ok(interviewService.deleteInterview(id));
    }

    @Operation(summary = "이력서 삭제", description = "ID값으로 이력서 삭제하기")
    @DeleteMapping("/resume/{id}")
    public ResponseEntity<String> deleteResume(@PathVariable("id") int id) {
        int result = resumeService.deleteResume(id); // 서비스 메서드 호출

        if (result == 0) {
            // 기본 이력서라서 삭제 불가
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("기본 이력서는 삭제할 수 없습니다.");
        } else {
            // 성공적으로 삭제
            return ResponseEntity.ok("이력서 삭제가 완료되었습니다.");
        }
    }

    @Operation(summary = "비밀번호 확인", description = "ID값으로 현재 비밀번호와 chkPw가 일치하는지 검사하기")
    @GetMapping("/password-check/{id}")
    public ResponseEntity<String> checkPwd(@PathVariable("id") int id, @RequestParam("usPw") String chkPw) {
        boolean isPwdCorrect = jobseekerService.checkPwd(id, chkPw);
        if (isPwdCorrect) {
            return ResponseEntity.ok("pwd match");
        } else {
            return ResponseEntity.badRequest().body("pwd mismatch");
        }
    }

    @Operation(summary = "비밀번호 수정", description = "ID값으로 비밀번호를 newPwd로 수정하기")
    @PutMapping("/password/{id}")
    public ResponseEntity<String> updatePassword(@PathVariable("id") int id, @RequestParam("newPwd") String newPwd) {
        boolean isUpdated = jobseekerService.updatePassword(id, newPwd);
        if (isUpdated) {
            return ResponseEntity.ok("change pwd success");
        } else {
            return ResponseEntity.badRequest().body("change pwd fail");
        }
    }

    @Operation(summary = "회원탈퇴", description = "ID값으로 회원탈퇴하기")
    @DeleteMapping("/account/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id, HttpServletResponse response) {
        boolean isDeleted = jobseekerService.deleteUser(id);

        if (isDeleted) {
            Cookie accessTokenCookie = new Cookie("accessToken", null);
            accessTokenCookie.setHttpOnly(true);
            accessTokenCookie.setPath("/");
            accessTokenCookie.setMaxAge(0); // 쿠키 만료 시간 설정 (0: 즉시 만료)

            Cookie refreshTokenCookie = new Cookie("refreshToken", null);
            refreshTokenCookie.setHttpOnly(true);
            refreshTokenCookie.setPath("/");
            refreshTokenCookie.setMaxAge(0); // 쿠키 만료 시간 설정 (0: 즉시 만료)

            // 응답에 쿠키 추가
            response.addCookie(accessTokenCookie);
            response.addCookie(refreshTokenCookie);

            return ResponseEntity.ok("delete account success");
        } else {
            return ResponseEntity.badRequest().body("delete account fail");
        }
    }

    @Operation(summary = "구직자 아이디 찾기", description = "구직자 이름과 전화번호로 구직자 아이디 찾기")
    @GetMapping("/search/id")
    public ResponseEntity<String> findUserId(@RequestParam("joName") String joName, @RequestParam("joTel") String joTel) {
        String userId = jobseekerService.getJobseekerUserId(joName, joTel);
        if (userId != null) {
            return ResponseEntity.ok(userId);
        } else {
            return ResponseEntity.badRequest().body("user not found");
        }
    }
}