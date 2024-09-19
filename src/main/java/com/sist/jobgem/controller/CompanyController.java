package com.sist.jobgem.controller;

import com.sist.jobgem.dto.*;
import com.sist.jobgem.service.*;

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
    @Autowired
    private InterviewService interviewService;
    @Autowired
    private OfferService offerService;
    @Autowired
    private BlackListService blackListService;

    @GetMapping("")
    public ResponseEntity<CompanyIndexDto> Index(@RequestParam int id) {
        return ResponseEntity.ok(companyService.getCompany(id));
    }

    @PostMapping("/update/logo")
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
    public ResponseEntity<Page<ReviewDto>> getReviewListByCoIdx(int coIdx, int loadPage) {
        PageRequest pageRequest = PageRequest.of(loadPage, 3, Sort.by(Sort.Direction.DESC, "reWriteDate"));
        return ResponseEntity.ok(reviewService.getReviewListByCoIdx(coIdx, pageRequest));
    }

    @GetMapping("/blackList")
    public Page<BlockDto> getBlackList(@RequestBody Pageable pageable, @RequestParam(required = false) String value, @RequestParam(required = false) String type) {
        return blockService.blackcompanyList(pageable, value, type);
    }

    @PostMapping("/blackList/add")
    public ResponseEntity<Integer> addBlackList(@RequestBody BlackListRequestDto requestDto){
        return ResponseEntity.ok(blackListService.addBlackList(requestDto));
    }

    @GetMapping("/notBlack")
    public List<CompanyDto> notBlack(@RequestBody @RequestParam(required = false) String type, @RequestParam(required = false) String value) {
        return companyService.notBlack(type, value);
    }

    @GetMapping("/addcompanyBlock")
    public BlockDto addcompanyBlock(@RequestBody BlockDto dto) {
        return blockService.addcompanyBlock(dto);
    }
    
    @GetMapping("deletecompanyBlock")
    public int deletecompanyBlock(@RequestParam List<String> chkList) {
        for (int i = 0; i < chkList.size(); i++) {
            blockService.deletecomjobBlock(Integer.parseInt(chkList.get(i)));
        }
        return chkList.size();
    }
  
    @GetMapping("/interview")
    public ResponseEntity<Page<InterviewDto>> getInterviewListByCoIdx(@RequestParam int id, @RequestParam int loadPage, @RequestParam String sortBy) {
        PageRequest pageable = PageRequest.of(loadPage, 3, Sort.by(Sort.Direction.DESC, sortBy));
        return ResponseEntity.ok(interviewService.getInterviewListByCoIdx(id, pageable));
    }

    @PostMapping("/offer/add")
    public ResponseEntity<OfferResponseDto> addOffer(@RequestBody OfferDto offerDto) {
        return ResponseEntity.ok(offerService.addOffer(offerDto));
    }

}
