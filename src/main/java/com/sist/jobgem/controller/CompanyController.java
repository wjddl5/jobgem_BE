package com.sist.jobgem.controller;

import com.sist.jobgem.dto.*;
import com.sist.jobgem.service.*;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
    @Autowired
    private InterviewService interviewService;
    @Autowired
    private OfferService offerService;
    @Autowired
    private BlackListService blackListService;

    @GetMapping("")
    public ResponseEntity<CompanyIndexDto> Index(@RequestParam("id") int id) {
        return ResponseEntity.ok(companyService.getCompany(id));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable("id") int id) {
        System.out.println("id = " + id);
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }
    
    @PutMapping("")
    public ResponseEntity<String> putCompany(@RequestBody CompanyDto companyDto) {
        int result = companyService.updateCompany(companyDto);
        if(result == companyDto.getId()){
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.badRequest().body("fail");
    }

    @PutMapping("/logo")
    public ResponseEntity<Integer> updateCompany(@RequestParam("id") int id, @RequestParam("coThumbimgUrl") String coThumbimgUrl) {
        return ResponseEntity.ok(companyService.updateCompany(id, coThumbimgUrl));
    }

    @GetMapping("/fit")
    public ResponseEntity<Slice<JobseekerDto>> getFitJobseekerList(@RequestParam int id,@RequestParam int loadPage) {
        PageRequest pageable = PageRequest.of(loadPage, 15, Sort.by(Sort.Direction.DESC, "joName"));
        return ResponseEntity.ok(companyService.getFitJobseekerList(id, pageable));
    }

    @GetMapping("/wish")
    public ResponseEntity<Slice<TalentDto>> getWishJobseekerList(@RequestParam int id, @RequestParam int loadPage) {
        PageRequest pageable = PageRequest.of(loadPage, 15, Sort.by(Sort.Direction.DESC, "joIdx"));
        return ResponseEntity.ok(companyService.getWishjobseekerList(id, pageable));
    }

    @PostMapping("/wish")
    public ResponseEntity<Integer> addWishJobseeker(@RequestBody TalentDto request) {
        return ResponseEntity.ok(talentService.addTalent(request));
    }

    @DeleteMapping("/wish")
    public void deleteWishJobseeker(@RequestParam int id) {
        talentService.removeTalent(id);
    }

    @GetMapping("/block")
    public ResponseEntity<Page<BlockDto>> getBlockListbyCoIdxAndJoName(@RequestParam int id, @RequestParam String name,@RequestParam int loadPage) {
        PageRequest pageable = PageRequest.of(loadPage, 5, Sort.by(Sort.Direction.DESC, "blDate"));
        return ResponseEntity.ok(blockService.getBlockListByCoIdxAndJoName(id, name, pageable));
    }

    @DeleteMapping("/block")
    public void deleteBlock(@RequestBody int[] selectList) {
        blockService.deleteBlock(selectList);
    }

    @GetMapping("/review")
    public ResponseEntity<Page<ReviewDto>> getReviewListByCoIdx(int coIdx, int loadPage) {
        PageRequest pageRequest = PageRequest.of(loadPage, 3, Sort.by(Sort.Direction.DESC, "reWriteDate"));
        return ResponseEntity.ok(reviewService.getReviewListByCoIdx(coIdx, pageRequest));
    }

    @PostMapping("/blacklist")
    public ResponseEntity<Integer> addBlackList(@RequestBody BlackListRequestDto requestDto){
        return ResponseEntity.ok(blackListService.addBlackList(requestDto));
    }

    @GetMapping("/interview")
    public ResponseEntity<Page<InterviewDto>> getInterviewListByCoIdx(@RequestParam int id, @RequestParam int loadPage, @RequestParam String sortBy) {
        PageRequest pageable = PageRequest.of(loadPage, 3, Sort.by(Sort.Direction.DESC, sortBy));
        return ResponseEntity.ok(interviewService.getInterviewListByCoIdx(id, pageable));
    }

    @PostMapping("/offer")
    public ResponseEntity<OfferResponseDto> addOffer(@RequestBody OfferDto offerDto) {
        return ResponseEntity.ok(offerService.addOffer(offerDto));
    }

    @DeleteMapping("/leave")
    public ResponseEntity<Integer> deleteCompany(@RequestParam int id) {
        return ResponseEntity.ok(companyService.deleteCompany(id));
    }
    
}
