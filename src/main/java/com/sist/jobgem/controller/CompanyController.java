package com.sist.jobgem.controller;

import com.sist.jobgem.dto.*;
import com.sist.jobgem.service.BlockService;
import com.sist.jobgem.service.CompanyService;

import com.sist.jobgem.service.ReviewService;
import com.sist.jobgem.service.TalentService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private TalentService talentService;
    @Autowired
    private BlockService blockService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("")
    public ResponseEntity<CompanyIndexDto> Index(@RequestParam int id) {
        return ResponseEntity.ok(companyService.getCompany(id));
    }

    @GetMapping("/fit")
    public ResponseEntity<FitJobseekerDto> getFitJobseekerList(int id, int loadPage) {
        PageRequest pageable = PageRequest.of(loadPage, 15, Sort.by(Sort.Direction.DESC, "joName"));
        return ResponseEntity.ok(companyService.getFitJobseekerList(id, pageable));
    }

    @GetMapping("/wish")
    public ResponseEntity<Slice<TalentDto>> getWishJobseekerList(@RequestParam int id, @RequestParam int loadPage) {
        PageRequest pageable = PageRequest.of(loadPage, 15, Sort.by(Sort.Direction.DESC, "joIdx"));
        return ResponseEntity.ok(companyService.getWishjobseekerList(id, pageable));
    }

    @PostMapping("/wish/add")
    public ResponseEntity<Integer> addWishJobseeker(@RequestBody TalentDto request) {
        return ResponseEntity.ok(talentService.addTalent(request));
    }

    @PostMapping("/wish/delete")
    public void removeWishJobseeker(@RequestBody int id) {
        talentService.removeTalent(id);
    }

    @GetMapping("/block")
    public ResponseEntity<Page<BlockDto>> getBlockListbyCoIdxAndJoName(@RequestParam int id, @RequestParam String name,@RequestParam int loadPage) {
        PageRequest pageable = PageRequest.of(loadPage, 5, Sort.by(Sort.Direction.DESC, "blDate"));
        return ResponseEntity.ok(blockService.getBlockListByCoIdxAndJoName(id, name, pageable));
    }

    @PostMapping("/block/delete")
    public void deleteBlock(@RequestBody int[] selectList) {
        blockService.deleteBlock(selectList);
    }

    @GetMapping("/list")
    public Page<CompanyDto> getCompanyList(@RequestBody Pageable pageable, @RequestParam(required = false) String value, @RequestParam(required = false) String type) {
        return companyService.getCompanyList(pageable, value, type);
    }

    @GetMapping("/review")
    public ResponseEntity<List<ReviewDto>> getReviewListByCoIdx(int coIdx) {
        return ResponseEntity.ok(reviewService.getReviewListByCoIdx(coIdx));
    }
}
