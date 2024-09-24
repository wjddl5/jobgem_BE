package com.sist.jobgem.controller;

import com.sist.jobgem.dto.*;
import com.sist.jobgem.service.*;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Company", description = "기업 API")
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

    @Operation(summary = "기업 메인 페이지", description = "기업 idx로 기업 정보를 가져옵니다.")
    @GetMapping("")
    public ResponseEntity<CompanyIndexDto> Index(@RequestParam("id") int id) {
        return ResponseEntity.ok(companyService.getCompany(id));
    }

    @Operation(summary = "특정 기업 정보 가져오기", description = "Path 변수로 기업 id를 받아 기업 상세 정보를 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable("id") int id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    @Operation(summary = "기업 정보 수정", description = "기업 정보를 수정합니다.")
    @PutMapping("")
    public ResponseEntity<String> putCompany(@RequestBody CompanyDto companyDto) {
        int result = companyService.updateCompany(companyDto);
        if(result == companyDto.getId()){
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.badRequest().body("fail");
    }

    @Operation(summary = "기업 로고 수정", description = "기업의 id와 새로운 로고 URL을 받아 로고를 수정합니다.")
    @PutMapping("/logo")
    public ResponseEntity<Integer> updateCompany(@RequestParam("id") int id, @RequestParam("coThumbimgUrl") String coThumbimgUrl) {
        return ResponseEntity.ok(companyService.updateCompany(id, coThumbimgUrl));
    }

    @Operation(summary = "적합한 구직자 목록 조회", description = "기업 id에 적합한 구직자를 페이지로 가져옵니다.")
    @GetMapping("/fit")
    public ResponseEntity<Slice<JobseekerDto>> getFitJobseekerList(@RequestParam int id, @RequestParam int loadPage) {
        PageRequest pageable = PageRequest.of(loadPage, 15, Sort.by(Sort.Direction.DESC, "joName"));
        return ResponseEntity.ok(companyService.getFitJobseekerList(id, pageable));
    }

    @Operation(summary = "찜한 구직자 목록 조회", description = "기업 id에 찜한 구직자 목록을 페이지로 가져옵니다.")
    @GetMapping("/wish")
    public ResponseEntity<Slice<TalentDto>> getWishJobseekerList(@RequestParam int id, @RequestParam int loadPage) {
        PageRequest pageable = PageRequest.of(loadPage, 15, Sort.by(Sort.Direction.DESC, "joIdx"));
        return ResponseEntity.ok(companyService.getWishjobseekerList(id, pageable));
    }

    @Operation(summary = "구직자 찜 추가", description = "구직자를 찜 목록에 추가합니다.")
    @PostMapping("/wish")
    public ResponseEntity<Integer> addWishJobseeker(@RequestBody TalentDto request) {
        return ResponseEntity.ok(talentService.addTalent(request));
    }

    @Operation(summary = "구직자 찜 삭제", description = "구직자를 찜 목록에서 삭제합니다.")
    @DeleteMapping("/wish")
    public void deleteWishJobseeker(@RequestParam int id) {
        talentService.removeTalent(id);
    }

    @Operation(summary = "차단 목록 조회", description = "기업 id와 구직자 이름을 받아 차단 목록을 조회합니다.")
    @GetMapping("/block")
    public ResponseEntity<Page<BlockDto>> getBlockListbyCoIdxAndJoName(@RequestParam int id, @RequestParam String name, @RequestParam int loadPage) {
        PageRequest pageable = PageRequest.of(loadPage, 5, Sort.by(Sort.Direction.DESC, "blDate"));
        return ResponseEntity.ok(blockService.getBlockListByCoIdxAndJoName(id, name, pageable));
    }

    @Operation(summary = "차단 목록에서 삭제", description = "선택된 구직자를 차단 목록에서 삭제합니다.")
    @DeleteMapping("/block")
    public void deleteBlock(@RequestBody int[] selectList) {
        blockService.deleteBlock(selectList);
    }

    @Operation(summary = "리뷰 목록 조회", description = "기업 id로 리뷰 목록을 가져옵니다.")
    @GetMapping("/review")
    public ResponseEntity<Page<ReviewDto>> getReviewListByCoIdx(int coIdx, int loadPage) {
        PageRequest pageRequest = PageRequest.of(loadPage, 3, Sort.by(Sort.Direction.DESC, "reWriteDate"));
        return ResponseEntity.ok(reviewService.getReviewListByCoIdx(coIdx, pageRequest));
    }

    @Operation(summary = "블랙리스트 추가", description = "블랙리스트에 구직자를 추가합니다.")
    @PostMapping("/blacklist")
    public ResponseEntity<Integer> addBlackList(@RequestBody BlackListRequestDto requestDto){
        return ResponseEntity.ok(blackListService.addBlackList(requestDto));
    }

    @Operation(summary = "면접 목록 조회", description = "기업 id로 면접 목록을 가져옵니다.")
    @GetMapping("/interview")
    public ResponseEntity<Page<InterviewDto>> getInterviewListByCoIdx(@RequestParam int id, @RequestParam int loadPage, @RequestParam String sortBy) {
        PageRequest pageable = PageRequest.of(loadPage, 3, Sort.by(Sort.Direction.DESC, sortBy));
        return ResponseEntity.ok(interviewService.getInterviewListByCoIdx(id, pageable));
    }

    @Operation(summary = "오퍼 추가", description = "기업이 구직자에게 오퍼를 보냅니다.")
    @PostMapping("/offer")
    public ResponseEntity<OfferResponseDto> addOffer(@RequestBody OfferDto offerDto) {
        return ResponseEntity.ok(offerService.addOffer(offerDto));
    }

    @Operation(summary = "기업 삭제", description = "기업 id로 해당 기업 정보를 삭제합니다.")
    @DeleteMapping("/leave")
    public ResponseEntity<Integer> deleteCompany(@RequestParam int id) {
        return ResponseEntity.ok(companyService.deleteCompany(id));
    }
}
