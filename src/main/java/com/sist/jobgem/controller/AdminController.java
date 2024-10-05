package com.sist.jobgem.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sist.jobgem.dto.*;
import com.sist.jobgem.entity.*;
import com.sist.jobgem.service.*;

import com.sist.jobgem.service.BlackListService;
import com.sist.jobgem.service.BlockService;
import com.sist.jobgem.service.BoardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Admin", description = "관리자 API")
@RestController
@RequestMapping("api/admin")
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;
    @Autowired
    PostService postService;
    @Autowired
    BlockService blockService;
    @Autowired
    JobseekerService jobseekerService;
    @Autowired
    BoardService boardService;
    @Autowired
    BlackListService blackListService;

    @Operation(summary = "모든 유저 조회", description = "모든 유저를 조회합니다.")
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @Operation(summary = "모든 구직자 조회", description = "모든 구직자를 조회합니다.")
    @GetMapping("/jobseekers")
    public ResponseEntity<Page<JobseekerDto>> getAllJobseekers(@RequestParam Map<String, Object> params) {
        return ResponseEntity.ok(jobseekerService.getJobseekerList(params));
    }

    @Operation(summary = "모든 공고 조회", description = "모든 공고를 조회합니다.")
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.findAllPosts());
    }

    @Operation(summary = "모든 기업 조회", description = "모든 기업을 조회합니다.")
    @GetMapping("/companies")
    public ResponseEntity<Page<CompanyDto>> getAllCompanies(@RequestParam Map<String, Object> params) {
        return ResponseEntity.ok(companyService.findAllCompanies(params));
    }

    @Operation(summary = "구직자 차단 목록 조회", description = "구직자 차단 목록을 조회합니다.")
    @GetMapping("/blocked-jobseekers")
    public ResponseEntity<Page<BlockDto>> getJobseekerBlocklist(@RequestParam Map<String, Object> params) {
        return ResponseEntity.ok(blockService.findAllJobseekerBlocks(params));
    }

    @Operation(summary = "기업 차단 목록 조회", description = "기업 차단 목록을 조회합니다.")
    @GetMapping("/blocked-companies")
    public ResponseEntity<Page<BlockDto>> getCompanyBlocklist(@RequestParam Map<String, Object> params) {
        return ResponseEntity.ok(blockService.findAllCompanyBlocks(params));
    }

    @Operation(summary = "차단 해제 구직자 조회", description = "차단 해제된 구직자를 조회합니다.")
    @GetMapping("/unblocked-jobseekers")
    public ResponseEntity<List<JobseekerDto>> getUnblockedJobseeker(@RequestParam Map<String, Object> params) {
        return ResponseEntity.ok(jobseekerService.findUnblockedJobseeker(params));
    }

    @Operation(summary = "차단 해제 기업 조회", description = "차단 해제된 기업을 조회합니다.")
    @GetMapping("/unblocked-companies")
    public ResponseEntity<List<CompanyDto>> getUnblockedCompany(@RequestParam Map<String, Object> params) {
        return ResponseEntity.ok(companyService.findUnblockedCompany(params));
    }

    @Operation(summary = "구직자 차단", description = "구직자를 차단합니다.")
    @PostMapping("/jobseeker-blocks")
    public ResponseEntity<Block> addjobseekerBlock(@RequestBody BlockDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(blockService.addjobseekerBlock(dto));
    }

    @Operation(summary = "구직자 차단 해제", description = "구직자 차단을 해제합니다.")
    @DeleteMapping("/jobseeker-blocks")
    public int deletejobseekerBlock(@RequestParam(value = "chkList", required = true) List<String> chkList) {
        for (int i = 0; i < chkList.size(); i++) {
            blockService.deletecomjobBlock(Integer.parseInt(chkList.get(i)));
        }
        return chkList.size();
    }

    @Operation(summary = "기업 차단", description = "기업을 차단합니다.")
    @PostMapping("/company-blocks")
    public ResponseEntity<Block> addcompanyBlock(@RequestBody BlockDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(blockService.addcompanyBlock(dto));
    }

    @Operation(summary = "기업 차단 해제", description = "기업 차단을 해제합니다.")
    @DeleteMapping("/company-blocks")
    public int deletecompanyBlock(@RequestParam(value = "chkList", required = true) List<String> chkList) {
        for (int i = 0; i < chkList.size(); i++) {
            blockService.deletecomjobBlock(Integer.parseInt(chkList.get(i)));
        }
        return chkList.size();
    }

    @Operation(summary = "미답변 문의 조회", description = "미답변 문의를 조회합니다.")
    @GetMapping("unanswered-questions")
    public ResponseEntity<List<BoardDto>> getQnaList() {
        return ResponseEntity.ok(boardService.getQnaList());
    }

    @Operation(summary = "미처리 신고 조회", description = "미처리 신고를 조회합니다.")
    @GetMapping("pending-blacklist")
    public ResponseEntity<List<BlackListDto>> getPendingBlackList() {
        return ResponseEntity.ok(blackListService.findPendingBlackList());
    }

    @GetMapping("/password/{id}")
    public ResponseEntity<String> checkPwd(@PathVariable("id") int id, @RequestParam("usPw") String chkPw) {
        boolean isPwdCorrect = userService.checkPwd(id, chkPw);
        if (isPwdCorrect) {
            return ResponseEntity.ok("pwd match");
        } else {
            return ResponseEntity.badRequest().body("pwd mismatch");
        }
    }

    @PutMapping("/password/{id}")
    public ResponseEntity<String> updatePwd(@PathVariable("id") int id, @RequestParam("newPwd") String newPwd) {
        boolean isUpdated = userService.updatePwd(id, newPwd);
        if (isUpdated) {
            return ResponseEntity.ok("change pwd success");
        } else {
            return ResponseEntity.badRequest().body("change pwd fail");
        }
    }
}
