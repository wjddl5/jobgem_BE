package com.sist.jobgem.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.jobgem.dto.CompanyJoinRequest;
import com.sist.jobgem.dto.JobseekerJoinRequest;
import com.sist.jobgem.entity.User;
import com.sist.jobgem.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;
    
    @GetMapping("/user")
    public ResponseEntity<User> all(int id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
    
    @GetMapping("/join/check/email")
    public ResponseEntity<Boolean> checkEmail(@Email @RequestParam String email) {
        return ResponseEntity.ok(userService.checkEmail(email));
    }
    
    @PostMapping("/join/jobseeker")
    public ResponseEntity<Integer> joinJobseeker(@Valid @RequestBody JobseekerJoinRequest request) {
        int result = userService.addJobseeker(request.getUser(), request.getJobseeker());
        if (result == 0) {
            return ResponseEntity.badRequest().body(0);
        }
        return ResponseEntity.created(URI.create("/api/join/jobseeker")).body(result);
    }

    @PostMapping("/join/company")
    public ResponseEntity<Integer> joinCompany(@Valid @RequestBody CompanyJoinRequest request) {
        int result = userService.addCompany(request.getUser(), request.getCompany());
        if (result == 0) {
            return ResponseEntity.badRequest().body(0);
        }
        return ResponseEntity.created(URI.create("/api/join/company")).body(result);
    }
}
