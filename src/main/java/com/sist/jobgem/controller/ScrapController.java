package com.sist.jobgem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.jobgem.dto.ScrapResponse;
import com.sist.jobgem.service.ScrapService;

@RestController
@RequestMapping("/api/scrap")
public class ScrapController {

    @Autowired
    private ScrapService scrapService;

    @GetMapping("/list/{joIdx}")
    public ResponseEntity<List<ScrapResponse>> getScrapByJoIdx(@PathVariable("joIdx") Integer joIdx) {
        List<ScrapResponse> scraps = scrapService.getScrapByJoIdx(joIdx);   
        return ResponseEntity.ok(scraps);
    }

    @GetMapping("/{poIdx}")
    public ResponseEntity<Boolean> isScrapByJoIdxAndPoIdx(@PathVariable("poIdx") Integer poIdx, @RequestParam("joIdx") Integer joIdx) {
        boolean isScrap = scrapService.isScrapByJoIdxAndPoIdx(joIdx, poIdx);
        return ResponseEntity.ok(isScrap);
    }

    @PostMapping("/{poIdx}")
    public ResponseEntity<String> managementScrap(@PathVariable("poIdx") Integer poIdx, @RequestParam("joIdx") Integer joIdx) {
        String result = scrapService.managementScrap(joIdx, poIdx);
        return ResponseEntity.ok(result);
    }
}
