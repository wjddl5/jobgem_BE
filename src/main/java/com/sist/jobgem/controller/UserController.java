package com.sist.jobgem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.jobgem.dto.CompanyJoinRequest;
import com.sist.jobgem.dto.JobseekerJoinRequest;
import com.sist.jobgem.entity.User;
import com.sist.jobgem.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;
    
    @GetMapping("/user")
    public ResponseEntity<User> all(int id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping("/join/emailcheck")
    public String postMethodName(@RequestBody String entity) {
        return entity;
    }
    
    @PostMapping("/join/jobseeker")
    public ResponseEntity<Integer> joinJobseeker(@Valid @RequestBody JobseekerJoinRequest request) {
        return ResponseEntity.ok(userService.addJobseeker(request.getUser(), request.getJobseeker()));
    }

    @PostMapping("/join/company")
    public ResponseEntity<Integer> joinCompany(@Valid @RequestBody CompanyJoinRequest request) {
        return ResponseEntity.ok(userService.addCompany(request.getUser(), request.getCompany()));
    }
}
