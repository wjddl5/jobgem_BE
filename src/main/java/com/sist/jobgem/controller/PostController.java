package com.sist.jobgem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.sist.jobgem.dto.JobseekerDto;
import com.sist.jobgem.service.JobseekerService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.sist.jobgem.dto.PostDto;
import com.sist.jobgem.dto.PostListDto;
import com.sist.jobgem.dto.PostSetDto;
import com.sist.jobgem.dto.PostWriteDto;
import com.sist.jobgem.dto.ResumeDto;
import com.sist.jobgem.dto.WorkSchedulesDto;
import com.sist.jobgem.entity.WorkDay;
import com.sist.jobgem.service.ApplymentService;
import com.sist.jobgem.service.PostService;
import com.sist.jobgem.service.ResumeService;
import com.sist.jobgem.dto.ApplymentDto;
import com.sist.jobgem.dto.PostCountApplyDto;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.sist.jobgem.service.WorkDayService;
import com.sist.jobgem.service.WorkSchedulesService;

@RestController
@RequestMapping("/api/post")
public class PostController {
    
    @Autowired
    private PostService postService;

    @Autowired
    private ApplymentService applymentService;

    @Autowired
    private WorkDayService workDayService;

    @Autowired
    private WorkSchedulesService workSchedulesService;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private JobseekerService jobseekerService;

    @RequestMapping("")
    public ResponseEntity<Page<PostCountApplyDto>> getPosts(@RequestParam Map<String, Object> map) {
        int coIdx = 1;
        return ResponseEntity.ok(postService.getPosts(map, coIdx));
    }

    @RequestMapping("/info")
    public ResponseEntity<Map<String, Object>> getPostListInfo(@RequestParam(value = "coIdx", required = true) int coIdx) {
        return ResponseEntity.ok(postService.getPostListInfo(coIdx));
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String writePost(@RequestBody PostWriteDto data) {
        PostDto pvo = new PostDto(data);
        int result = postService.create(pvo);
        WorkSchedulesDto workSchedulesDto = new WorkSchedulesDto();
        workSchedulesDto.setPoIdx(result);
        workSchedulesDto.setWsStartTime(data.getWorkStartTime());
        workSchedulesDto.setWsEndTime(data.getWorkEndTime());
        List<WorkDay> workDays = workDayService.getWorkIdIn(data.getWorkDay());
        workSchedulesDto.setWorkDays(workDays);
        workSchedulesService.create(workSchedulesDto);
        return "success";
    }

    @RequestMapping(value = "/set", method = RequestMethod.GET)
    public ResponseEntity<PostSetDto> getPostSet() {
        return ResponseEntity.ok(postService.getPostSet());
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ResponseEntity<PostDto> getPost(@RequestParam(value = "poIdx", required = true) int poIdx) {
        return ResponseEntity.ok(postService.getPost(poIdx));
    }

    @RequestMapping(value = "/apply", method = RequestMethod.GET)
    public ResponseEntity<Page<ApplymentDto>> getApply(@RequestParam(value = "id", required = true) int id, @RequestParam(value = "curPage", required = true) int curPage) {
        PageRequest pageable = PageRequest.of(curPage, 5,
                Sort.by(Sort.Direction.DESC, "id"));
                
        return ResponseEntity.ok(applymentService.getApplymentListByPoIdx(id, pageable));
    }
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getPostDetail(@RequestParam(value = "id", required = true) int id) {
        return ResponseEntity.ok(postService.getDetail(id));
    }
    @RequestMapping(value = "/resume", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getResume(@RequestParam(value = "id", required = true) int id) {
        Map<String, Object> map = new HashMap<>();
        applymentService.view(id);
        ResumeDto resume = resumeService.getResume(id);
        JobseekerDto jobseeker = jobseekerService.getJobseeker(id);
        map.put("resume", resume);
        map.put("jobseeker", jobseeker);
        return ResponseEntity.ok(map);
    }
}
