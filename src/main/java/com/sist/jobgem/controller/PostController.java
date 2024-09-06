package com.sist.jobgem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;


import com.sist.jobgem.dto.PostDto;
import com.sist.jobgem.dto.PostSetDto;
import com.sist.jobgem.dto.PostWriteDto;
import com.sist.jobgem.dto.WorkSchedulesDto;
import com.sist.jobgem.entity.WorkDay;
import com.sist.jobgem.service.PostService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import com.sist.jobgem.service.WorkDayService;
import com.sist.jobgem.service.WorkSchedulesService;

import java.time.LocalDate;
@RestController
@RequestMapping("/api/post")
public class PostController {
    
    @Autowired
    private PostService postService;

    @Autowired
    private WorkDayService workDayService;

    @Autowired
    private WorkSchedulesService workSchedulesService;

    @RequestMapping("")
    public ResponseEntity<List<PostDto>> writePost() {
        return ResponseEntity.ok(postService.getPosts());
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
}
