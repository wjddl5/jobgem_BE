package com.sist.jobgem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sist.jobgem.service.CompanyService;
import com.sist.jobgem.service.JobseekerService;
import com.sist.jobgem.service.PostService;
import com.sist.jobgem.service.UserService;
import com.sist.jobgem.service.BlackListService;
import com.sist.jobgem.service.BlockService;
import com.sist.jobgem.service.BoardService;
import com.sist.jobgem.dto.BlackListDto;
import com.sist.jobgem.dto.BlockDto;
import com.sist.jobgem.dto.BoardDto;
import com.sist.jobgem.dto.CompanyDto;
import com.sist.jobgem.dto.JobseekerDto;
import com.sist.jobgem.dto.PostDto;
import com.sist.jobgem.dto.UserDto;


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
    
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }
    
    @GetMapping("/companies")
    public ResponseEntity<Page<CompanyDto>> getAllCompanies(Pageable pageable, @RequestParam(required = false) String value, @RequestParam(required = false) String type) {
        return ResponseEntity.ok(companyService.findAllCompanies(pageable, value, type));
    }

    @GetMapping("/posts")
    public ResponseEntity<Page<PostDto>> getAllPosts(Pageable pageable, @RequestParam(required = false) String value, @RequestParam(required = false) String type) {
        return ResponseEntity.ok(postService.findAllPosts(pageable, value, type));
    }

    @GetMapping("/blocked-jobseekers")
    public ResponseEntity<Page<BlockDto>> getJobseekerBlocklist(Pageable pageable, @RequestParam(required = false) String value, @RequestParam(required = false) String type) {
        return ResponseEntity.ok(blockService.findAllJobseekerBlocks(pageable, value, type));
    }

    @GetMapping("/blocked-companies")
    public ResponseEntity<Page<BlockDto>> getCompanyBlocklist(Pageable pageable, @RequestParam(required = false) String value, @RequestParam(required = false) String type) {
        return ResponseEntity.ok(blockService.findAllCompanyBlocks(pageable, value, type));
    }

    @GetMapping("/unblocked-jobseekers")
    public ResponseEntity<List<JobseekerDto>> getUnblockedJobseeker(@RequestParam(required = false) String type, @RequestParam(required = false) String value) {
        return ResponseEntity.ok(jobseekerService.findUnblockedJobseeker(type, value));
    }

    @GetMapping("/unblocked-companies")
    public ResponseEntity<List<CompanyDto>> getUnblockedCompany(@RequestParam(required = false) String type, @RequestParam(required = false) String value) {
        return ResponseEntity.ok(companyService.findUnblockedCompany(type, value));
    }

    @PostMapping("/jobseeker-blocks")
    public void addjobseekerBlock(@RequestParam(value = "id") int id, @RequestParam(value = "value") String value) {
        blockService.addjobseekerBlock(id, value);
    }

    @DeleteMapping("/jobseeker-blocks")
    public int deletejobseekerBlock(@RequestParam List<String> chkList) {
        for (int i = 0; i < chkList.size(); i++) {
            blockService.deletecomjobBlock(Integer.parseInt(chkList.get(i)));
        }
        return chkList.size();
    }

    @PostMapping("/company-blocks")
    public void addcompanyBlock(@RequestParam(value = "id") int id, @RequestParam(value = "value") String value) {
        blockService.addcompanyBlock(id, value);
    }
    
    @DeleteMapping("/company-blocks")
    public int deletecompanyBlock(@RequestParam List<String> chkList) {
        for (int i = 0; i < chkList.size(); i++) {
            blockService.deletecomjobBlock(Integer.parseInt(chkList.get(i)));
        }
        return chkList.size();
    }
}

