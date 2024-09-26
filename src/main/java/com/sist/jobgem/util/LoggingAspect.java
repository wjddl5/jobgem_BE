package com.sist.jobgem.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sist.jobgem.service.LogService;
import com.sist.jobgem.util.jwt.JwtProvider;
import com.sist.jobgem.util.jwt.AccessTokenClaims;

@Aspect
@Component
public class LoggingAspect {

    @Autowired
    private LogService crudLogService; // 로그를 기록할 서비스

    @Autowired
    private JwtProvider jwtProvider; // JWT Provider를 통해 JWT 클레임을 추출

    // Create 메서드가 실행될 때
    @Pointcut("execution(* com.sist.jobgem.service.*.add*(..))")
    public void createOperation() {
    }

    // Update 메서드가 실행될 때
    @Pointcut("execution(* com.sist.jobgem.service.*.update*(..))")
    public void updateOperation() {
    }

    // Delete 메서드가 실행될 때
    @Pointcut("execution(* com.sist.jobgem.service.*.delete*(..))")
    public void deleteOperation() {
    }

    // Download 메서드가 실행될 때
    @Pointcut("execution(* com.sist.jobgem.service.*.download*(..))")
    public void downloadOperation() {
    }

    // Upload 메서드가 실행될 때
    @Pointcut("execution(* com.sist.jobgem.service.*.upload*(..))")
    public void uploadOperation() {
    }

    // JWT 토큰에서 idx와 role을 추출하는 메서드
    private AccessTokenClaims getClaimsFromJwt() {
        // JWT 토큰은 보통 Authorization 헤더에 있습니다.
        // 이를 가져와서 JwtProvider로부터 AccessTokenClaims를 추출
        String token = ""; // JWT 토큰을 요청 헤더에서 가져와야 함
        return jwtProvider.getAccessTokenClaims(token);
    }

    // Create 작업 후 로그 기록
    @AfterReturning(value = "createOperation()", returning = "entity")
    public void logCreateOperation(JoinPoint joinPoint, Object entity) {
        AccessTokenClaims claims = getClaimsFromJwt(); // JWT에서 claims 추출
        Integer usIdx = claims.getIdx(); // idx 추출
        Integer usType = claims.getRole(); // role 추출
        String loContent = "생성 완료: " + entity.getClass().getSimpleName();
        crudLogService.logAction(usIdx, usType, loContent); // 서비스 호출
    }

    // Update 작업 후 로그 기록
    @AfterReturning(value = "updateOperation()", returning = "entity")
    public void logUpdateOperation(JoinPoint joinPoint, Object entity) {
        AccessTokenClaims claims = getClaimsFromJwt(); // JWT에서 claims 추출
        Integer usIdx = claims.getIdx(); // idx 추출
        Integer usType = claims.getRole(); // role 추출
        String loContent = "업데이트 완료: " + entity.getClass().getSimpleName();
        crudLogService.logAction(usIdx, usType, loContent);
    }

    // Delete 작업 후 로그 기록
    @AfterReturning(value = "deleteOperation()")
    public void logDeleteOperation(JoinPoint joinPoint) {
        AccessTokenClaims claims = getClaimsFromJwt(); // JWT에서 claims 추출
        Integer usIdx = claims.getIdx(); // idx 추출
        Integer usType = claims.getRole(); // role 추출
        String loContent = "삭제 완료";
        crudLogService.logAction(usIdx, usType, loContent);
    }

    // Download 작업 후 로그 기록
    @AfterReturning(value = "downloadOperation()", returning = "entity")
    public void logDownloadOperation(JoinPoint joinPoint, Object entity) {
        AccessTokenClaims claims = getClaimsFromJwt(); // JWT에서 claims 추출
        Integer usIdx = claims.getIdx(); // idx 추출
        Integer usType = claims.getRole(); // role 추출
        String loContent = "다운로드 완료: " + entity.getClass().getSimpleName();
        crudLogService.logAction(usIdx, usType, loContent);
    }

    // Upload 작업 후 로그 기록
    @AfterReturning(value = "uploadOperation()", returning = "entity")
    public void logUploadOperation(JoinPoint joinPoint, Object entity) {
        AccessTokenClaims claims = getClaimsFromJwt(); // JWT에서 claims 추출
        Integer usIdx = claims.getIdx(); // idx 추출
        Integer usType = claims.getRole(); // role 추출
        String loContent = "업로드 완료: " + entity.getClass().getSimpleName();
        crudLogService.logAction(usIdx, usType, loContent);
    }
}
