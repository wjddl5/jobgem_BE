package com.sist.jobgem.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.sist.jobgem.service.WorkDayService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private ApplymentService applymentService;

    @Autowired
    private WorkDayService workDayService;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private JobseekerService jobseekerService;

    @GetMapping("")
    public ResponseEntity<Page<PostCountApplyDto>> getPosts(@RequestParam Map<String, Object> params) {
        int coIdx = 1; // Consider making this a path variable or part of the params
        return ResponseEntity.ok(postService.getPosts(params, coIdx));
    }

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getPostListInfo(@RequestParam int coIdx) {
        return ResponseEntity.ok(postService.getPostListInfo(coIdx));
    }

    @PostMapping("")
    public ResponseEntity<String> createPost(@RequestBody PostWriteDto data) {
        PostDto pvo = new PostDto(data);
        postService.create(pvo);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/set")
    public ResponseEntity<PostSetDto> getPostSet() {
        return ResponseEntity.ok(postService.getPostSet());
    }

    @GetMapping("/{poIdx}")
    public ResponseEntity<PostDto> getPost(@PathVariable int poIdx) {
        return ResponseEntity.ok(postService.getPost(poIdx));
    }

    @GetMapping("/{id}/applyments")
    public ResponseEntity<Page<ApplymentDto>> getApply(@PathVariable int id, @RequestParam int curPage) {
        PageRequest pageable = PageRequest.of(curPage, 5, Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(applymentService.getApplymentListByPoIdx(id, pageable));
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<Map<String, Object>> getPostDetail(@PathVariable int id) {
        return ResponseEntity.ok(postService.getDetail(id));
    }

    @GetMapping("/{id}/resume")
    public ResponseEntity<Map<String, Object>> getResume(@PathVariable int id) {
        Map<String, Object> map = new HashMap<>();
        applymentService.view(id);
        ResumeDto resume = resumeService.getResume(id);
        JobseekerDto jobseeker = jobseekerService.getJobseeker(id);
        map.put("resume", resume);
        map.put("jobseeker", jobseeker);
        return ResponseEntity.ok(map);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id) {
        int result = postService.delete(id);
        return result == 1 ? ResponseEntity.ok("success") : ResponseEntity.badRequest().body("fail");
    }

    @GetMapping("/applyments/search")
    public ResponseEntity<Page<ApplymentDto>> getApplymentListByFilters(@ModelAttribute ApplymentSearchDto dto,
            @RequestParam(required = false) int curPage) {
        PageRequest pageable = PageRequest.of(curPage, 5, Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(applymentService.searchApplymentwithJobseeker(dto, pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<PostDto>> searchPosts(@RequestParam(value = "keyword", required = true) String keyword, @RequestParam(value = "curPage", required = true) int curPage) {
        if(keyword.equals("")){
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(postService.searchPosts(keyword, curPage));
    }

    @PostMapping("/recruit")
    public ResponseEntity<Page<PostDto>> recruitPost(@RequestBody RecruitRequest recruitRequest) {
        return ResponseEntity.ok(postService.findByRecruit(recruitRequest));
    }
    
    @GetMapping("/all")
    public ResponseEntity<Page<PostDto>> getAllPosts(Pageable pageable, 
            @RequestParam(required = false) String type, 
            @RequestParam(required = false) String value) {
        return ResponseEntity.ok(postService.getAllPosts(pageable, type, value));
    }
    
}
