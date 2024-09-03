package com.sist.jobgem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.jobgem.dto.CompanyDto;
import com.sist.jobgem.dto.JobseekerDto;
import com.sist.jobgem.dto.UserDto;
import com.sist.jobgem.entity.User;
import com.sist.jobgem.service.UserService;

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
        //TODO: process POST request
        
        return entity;
    }
    
    @PostMapping("/join/jobseeker")
    public ResponseEntity<Integer> joinJobseeker(@RequestBody UserDto user, JobseekerDto jobseeker) {
        
        return ResponseEntity.ok(userService.addJobseeker(user, jobseeker));
    }

    @PostMapping("/join/company")
    public ResponseEntity<Integer> joinCompany(User user, CompanyDto company) {
        return ResponseEntity.ok(1);
    }
    
    
}
