package com.sist.jobgem.controller;

import com.sist.jobgem.dto.AlertResponseDto;
import com.sist.jobgem.service.AlertService;
import com.sist.jobgem.service.SseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/alert")
public class AlertController {
    private final SseService sseService;
    private final AlertService alertService;

    @GetMapping(value = "/subscribe/{userId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@PathVariable int userId) {
        return sseService.subscribe(userId);
    }

    @GetMapping(value = "/send/{userId}/{data}")
    public void send(@PathVariable int userId, @PathVariable Object data) {
        sseService.sendToClient(userId, data);
    }

    @GetMapping
    public ResponseEntity<List<AlertResponseDto>> findAll(@RequestParam int usIdx) {
        return ResponseEntity.ok(alertService.findAllByUsIdx(usIdx));
    }

    @PutMapping("/read")
    public void readAlerts(@RequestParam int usIdx){
        alertService.readAlerts(usIdx);
    }
}
