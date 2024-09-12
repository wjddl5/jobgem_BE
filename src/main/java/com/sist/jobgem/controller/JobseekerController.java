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

    @GetMapping("/jobseeker")
    public ResponseEntity<JobseekerDto> getJobseeker(@RequestParam(value = "id", required = true) int id) {
        return ResponseEntity.ok(jobseekerService.getJobseeker(id));
    }

    @GetMapping("/reviewList")
    public Page<ReviewDto> getReviewList(int id, int curPage) {
        PageRequest pageable = PageRequest.of(curPage, 5, Sort.by(Sort.Direction.DESC, "id"));
        return reviewService.getReviewList(id, pageable);
    }

    @GetMapping("/interviewList")
    public Page<InterviewDto> getInterviewist(int id, int curPage) {
        PageRequest pageable = PageRequest.of(curPage, 5, Sort.by(Sort.Direction.DESC, "id"));
        return interviewService.getInterviewist(id, pageable);
    }

    @GetMapping("/resumeList")
    public Page<ResumeDto> getresumeList(int id, int curPage) {
        PageRequest pageable = PageRequest.of(curPage, 5, Sort.by(Sort.Direction.DESC, "id"));
        return resumeService.getResumeList(id, pageable);
    }

    @GetMapping("/applymentList")
    public ResponseEntity<Page<ApplymentDto>> getApplymentList(@RequestParam int id, @RequestParam int curPage) {
        PageRequest pageable = PageRequest.of(curPage, 5,
                Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(applymentService.getApplymentList(id, pageable));
    }

    @GetMapping("/applymentSearch")
    public Page<ApplymentDto> getApplymentListByFilters(@ModelAttribute ApplymentSearchDto dto, @RequestParam(value="curPage", required = false)int curPage) {
        PageRequest pageable = PageRequest.of(curPage, 5, Sort.by(Sort.Direction.DESC, "id"));
        return applymentService.searchApplyment(dto, pageable);
    }

    @GetMapping("/applymentCount")
    public Map<String, Object> getApplymentCount(@RequestParam int id) {
        return applymentService.applymentCount(id);
    }

    @GetMapping("/offerList")
    public Page<OfferDto> getOfferList(int id, int curPage) {
        PageRequest pageable = PageRequest.of(curPage, 5, Sort.by(Sort.Direction.DESC, "id"));
        return offerService.getOfferList(id, pageable);
    }

    @GetMapping("/postList")
    public ResponseEntity<Slice<PostDto>> getPostList(@RequestParam int loadPage) {
        PageRequest pageable = PageRequest.of(loadPage, 15, Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(postService.getPostList(pageable));
    }

    @GetMapping("/companyList")
    public List<CompanyDto> getCompanyList() {
        return reviewService.getCompanyList();
    }

    @GetMapping("/skillList")
    public List<SkillDto> getSkillList() {
        return skillService.getSkillList();
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

    @GetMapping("/addApplyment")
    public ResponseEntity<Applyment> addApplyment(@RequestBody ApplymentDto applymentDto, @RequestParam int joIdx) {
        Applyment applyment = applymentService.addApplyment(applymentDto, joIdx);
        return ResponseEntity.ok(applyment);
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

    @GetMapping("/updateMypage")
    public Jobseeker updateJobseekerDetails(@RequestParam int id, @RequestBody JobseekerDto jobseekerDto) {
        return jobseekerService.updateJobseekerDetails(id, jobseekerDto);
    }

    @GetMapping("/updateDefaultResume")
    public ResponseEntity<String> updateDefaultResume(@RequestParam("id") int resumeId,
            @RequestParam("joIdx") int joIdx) {
        try {
            resumeService.updateDefaultResume(resumeId, joIdx); // 서비스에서 대표 이력서 업데이트 로직 실행
            return ResponseEntity.ok("1");
        } catch (Exception e) {
            return ResponseEntity.ok("0");
        }
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

    @GetMapping("/blackList")
    public Page<BlockDto> getBlackList(@RequestBody Pageable pageable, @RequestParam(required = false) String value,
            @RequestParam(required = false) String type) {
        return blockService.blackjobseekerList(pageable, value, type);
    }

    @GetMapping("/checkPwd")
    public ResponseEntity<String> checkPwd(@RequestParam("id") int id, @RequestParam("usPw") String chkPw) {
        boolean isPwdCorrect = jobseekerService.checkPwd(id, chkPw);
        if (isPwdCorrect) {
            return ResponseEntity.ok("1");
        } else {
            return ResponseEntity.ok("0");
        }
    }

    @GetMapping("/updatePwd")
    public ResponseEntity<String> updatePassword(@RequestParam("id") int id, @RequestParam("newPwd") String newPwd) {
        boolean isUpdated = jobseekerService.updatePassword(id, newPwd);
        if (isUpdated) {
            return ResponseEntity.ok("1"); // 비밀번호 변경 성공
        } else {
            return ResponseEntity.ok("0"); // 비밀번호 변경 실패
        }
    }

    @GetMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam("id") int id) {
        boolean isDeleted = jobseekerService.deleteUser(id);

        if (isDeleted) {
            return ResponseEntity.ok("1"); // 성공적으로 삭제된 경우 "1" 반환
        } else {
            return ResponseEntity.ok("0"); // 실패 시 "0" 반환
        }
    }
    
    @GetMapping("/notBlack")
    public List<JobseekerDto> notBlack(@RequestBody @RequestParam(required = false) String type,
            @RequestParam(required = false) String value) {
        return jobseekerService.notBlack(type, value);
    }
    
    @GetMapping("/addjobseekerBlock")
    public BlockDto addjobseekerBlock(@RequestBody BlockDto dto) {
        return blockService.addjobseekerBlock(dto);
    }
   
    @RequestMapping("deletejobseekerBlock")
    public int deletejobseekerBlock(@RequestParam List<String> chkList) {
        for (int i = 0; i < chkList.size(); i++) {
            blockService.deletecomjobBlock(Integer.parseInt(chkList.get(i)));
        }
        return chkList.size();
    }

}
