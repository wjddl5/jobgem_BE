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
import com.sist.jobgem.dto.LoginRequest;
import com.sist.jobgem.dto.LoginResponse;
import com.sist.jobgem.entity.User;
import com.sist.jobgem.enums.LoginStatusEnum;
import com.sist.jobgem.service.UserService;
import com.sist.jobgem.util.jwt.TokenDto;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        LoginResponse result = userService.login(request.getUsId(), request.getUsPw());

        if (result.getMsg() != LoginStatusEnum.LOGIN_SUCCESS.getMessage()) {
            return ResponseEntity.badRequest().body(result.getMsg());
        }

        TokenDto token = userService.createToken(result.getUser());

        Cookie accessTokenCookie = new Cookie("accessToken", token.getAccessToken());
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setPath("/");
        response.addCookie(accessTokenCookie);

        Cookie refreshTokenCookie = new Cookie("refreshToken", token.getRefreshToken());
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/");
        response.addCookie(refreshTokenCookie);

        return ResponseEntity.ok(result.getMsg());
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        // 쿠키 삭제를 위한 빈 쿠키 생성
        Cookie accessTokenCookie = new Cookie("accessToken", null);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(0); // 쿠키 만료 시간 설정 (0: 즉시 만료)

        Cookie refreshTokenCookie = new Cookie("refreshToken", null);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(0); // 쿠키 만료 시간 설정 (0: 즉시 만료)

        // 응답에 쿠키 추가
        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);

        return ResponseEntity.ok("로그아웃 되었습니다.");
    }
    
    @GetMapping("/join/check/email")
    public ResponseEntity<Boolean> checkEmail(@Email @RequestParam("email") String email) {
        return ResponseEntity.ok(userService.isEmailExist(email));
    }

    @PostMapping("/join/jobseeker")
    public ResponseEntity<Integer> joinJobseeker(@Valid @RequestBody JobseekerJoinRequest request) {
        int result = userService.joinJobseeker(request.getUser(), request.getJobseeker());
        if (result == 0) {
            return ResponseEntity.badRequest().body(0);
        }
        return ResponseEntity.created(URI.create("/api/join/jobseeker")).body(result);
    }

    @PostMapping("/join/company")
    public ResponseEntity<Integer> joinCompany(@Valid @RequestBody CompanyJoinRequest request) {
        int result = userService.joinCompany(request.getUser(), request.getCompany());
        if (result == 0) {
            return ResponseEntity.badRequest().body(0);
        }
        return ResponseEntity.created(URI.create("/api/join/company")).body(result);
    }
}
