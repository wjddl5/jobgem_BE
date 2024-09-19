package com.sist.jobgem.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.jobgem.dto.ChatRedisDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class RedisService {

    private static final Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 저장
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        logger.info("Redis에 키 저장: {}, 값: {}", key, value);
    }

    // 저장 타임아웃 시간 지정 저장
    public void set(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
        logger.info("Redis에 키 저장: {}, 값: {}", key, value);
    }

    // 조회
    public Object get(String key) {
        Object value = redisTemplate.opsForValue().get(key);
        logger.info("Redis에서 키 조회: {}, 값: {}", key, value);
        return value;
    }

    // 삭제
    public void delete(String key) {
        redisTemplate.delete(key);
        logger.info("Redis에서 키 삭제: {}", key);
    }

    // 여부 확인 없으면 db 가져오는 용도
    public boolean hasKey(String key) {
        boolean exists = Boolean.TRUE.equals(redisTemplate.hasKey(key));
        logger.info("Redis 키 존재 여부 확인: {}, 존재: {}", key, exists);
        return exists;
    }

    // 리스트에 값 저장
    public void addToList(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);  // 리스트의 맨 끝에 값 추가
        logger.info("Redis 리스트에 값 추가: 키: {}, 값: {}", key, value);
    }

    // 채팅 리스트 조회
    public List<ChatRedisDto> getChatList(String key) {
        // redis 저장값은 문자열이라 조회 후 DTO로 바꿔줘야함
        ObjectMapper objectMapper = new ObjectMapper();
        List<ChatRedisDto> values = Objects.requireNonNull(redisTemplate.opsForList().range(key, 0, -1)).stream()
                        .map(json -> objectMapper.convertValue(json, ChatRedisDto.class))
                        .collect(Collectors.toList());
        logger.info("Redis 리스트에서 값 조회: 키: {}, 값: {}", key, values);
        return values;
    }

    // 리스트에서 특정 값을 삭제
    public void removeFromList(String key, Object value) {
        redisTemplate.opsForList().remove(key, 1, value);  // 리스트에서 해당 값 1개 제거
        logger.info("Redis 리스트에서 값 삭제: 키: {}, 값: {}", key, value);
    }
}