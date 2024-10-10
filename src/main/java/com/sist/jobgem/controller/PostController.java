package com.sist.jobgem.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sist.jobgem.dto.ApplymentDto;
import com.sist.jobgem.dto.ApplymentSearchDto;
import com.sist.jobgem.dto.JobseekerDto;
import com.sist.jobgem.dto.PostCountApplyDto;
import com.sist.jobgem.dto.PostDto;
import com.sist.jobgem.dto.PostSetDto;
import com.sist.jobgem.dto.PostWriteDto;
import com.sist.jobgem.dto.RecruitRequest;
import com.sist.jobgem.dto.ResumeDto;
import com.sist.jobgem.service.ApplymentService;
import com.sist.jobgem.service.JobseekerService;
import com.sist.jobgem.service.PostService;
import com.sist.jobgem.service.ResumeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Post", description = "채용공고 API")
@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private ApplymentService applymentService;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private JobseekerService jobseekerService;

    @Operation(summary = "채용공고 목록 불러오기", description = "채용공고 목록을 페이지네이션하여 불러옵니다.",
               responses = {
                   @ApiResponse(responseCode = "200", description = "성공적으로 채용공고 목록을 불러옴",
                                content = @Content(schema = @Schema(implementation = Page.class)))
               })
    @GetMapping("")
    public ResponseEntity<Page<PostCountApplyDto>> getPosts(@Parameter(description = "페이지네이션 및 필터링 파라미터") @RequestParam Map<String, Object> params) {
        return ResponseEntity.ok(postService.getPosts(params));
    }
    
    @Operation(summary = "채용공고 정보 불러오기", description = "특정 회사의 채용공고 정보를 불러옵니다.",
               responses = {
                   @ApiResponse(responseCode = "200", description = "성공적으로 채용공고 정보를 불러옴",
                                content = @Content(schema = @Schema(implementation = Map.class)))
               })
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getPostListInfo(@Parameter(description = "회사 인덱스") @RequestParam("coIdx") int coIdx) {
        return ResponseEntity.ok(postService.getPostListInfo(coIdx));
    }

    @Operation(summary = "채용공고 생성 및 수정", description = "채용공고 생성 및 수정")
    @PostMapping("")
    public ResponseEntity<String> createPost(@RequestBody PostWriteDto data) {
        PostDto pvo = new PostDto(data);
        postService.create(pvo);
        return ResponseEntity.ok("success");
    }

    @Operation(summary = "채용공고 설정 불러오기", description = "채용공고 설정 불러오기")
    @GetMapping("/set")
    public ResponseEntity<PostSetDto> getPostSet() {
        return ResponseEntity.ok(postService.getPostSet());
    }

    @Operation(summary = "채용공고 정보 불러오기", description = "채용공고 정보 불러오기")
    @GetMapping("/{poIdx}")
    public ResponseEntity<PostDto> getPost(@PathVariable("poIdx") int poIdx) {
        return ResponseEntity.ok(postService.getPost(poIdx));
    }

    @Operation(summary = "채용공고 지원자 목록 불러오기", description = "채용공고 지원자 목록 불러오기")
    @GetMapping("/{id}/applyments")
    public ResponseEntity<Page<ApplymentDto>> getApply(@PathVariable("id") int id, @RequestParam("curPage") int curPage) {
        PageRequest pageable = PageRequest.of(curPage, 5, Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(applymentService.getApplymentListByPoIdx(id, pageable));
    }

    @Operation(summary = "채용공고 상세 정보 불러오기", description = "채용공고 상세 정보 불러오기")
    @GetMapping("/{id}/detail")
    public ResponseEntity<Map<String, Object>> getPostDetail(@PathVariable("id") int id) {
        return ResponseEntity.ok(postService.getDetail(id));
    }
    @Operation(summary = "이력서 열람", description = "채용공고에 지원한 이력서를 열람합니다.")
    @PutMapping("/{id}/read")
    public ResponseEntity<String> viewResume(@PathVariable("id") int id) {
        applymentService.view(id);
        return ResponseEntity.ok("이력서가 성공적으로 열람되었습니다.");
    }
    
    @Operation(summary = "채용공고 지원자 이력서 불러오기", description = "채용공고 지원자 이력서 불러오기")
    @GetMapping("/{id}/resume")
    public ResponseEntity<Map<String, Object>> getResume(@PathVariable("id") int id) {
        Map<String, Object> map = new HashMap<>();
        ResumeDto resume = resumeService.getResume(id);
        JobseekerDto jobseeker = jobseekerService.getJobseeker(resume.getJoIdx());
        map.put("resume", resume);
        map.put("jobseeker", jobseeker);
        return ResponseEntity.ok(map);
    }

    @Operation(summary = "채용공고 삭제", description = "채용공고 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@Parameter(description = "삭제할 채용공고 ID") @PathVariable("id") int id) {
        int result = postService.delete(id);
        return result == 1 ? ResponseEntity.ok("success") : ResponseEntity.badRequest().body("fail");
    }
    
    @Operation(summary = "채용공고 지원자 목록 검색", description = "채용공고 지원자 목록 검색")
    @GetMapping("/applyments/search")
    public ResponseEntity<Page<ApplymentDto>> getApplymentListByFilters(@ModelAttribute ApplymentSearchDto dto,
            @RequestParam(required = false) int curPage) {
        PageRequest pageable = PageRequest.of(curPage, 5, Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(applymentService.searchApplymentwithJobseeker(dto, pageable));
    }

    @Operation(summary = "채용공고 검색", description = "채용공고 검색")
    @GetMapping("/search")
    public ResponseEntity<Page<PostDto>> searchPosts(@RequestParam(value = "keyword", required = true) String keyword, @RequestParam(value = "curPage", required = true) int curPage) {
        if(keyword.equals("")){
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(postService.searchPosts(keyword, curPage));
    }

    @Operation(summary = "채용공고 채용 검색", description = "채용공고 채용 요청")
    @PostMapping("/recruit")
    public ResponseEntity<Slice<PostDto>> recruitPost(@RequestBody RecruitRequest recruitRequest) {
        return ResponseEntity.ok(postService.findByRecruit(recruitRequest));
    }
}
