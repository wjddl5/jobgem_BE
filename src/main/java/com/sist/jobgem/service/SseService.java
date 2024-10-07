package com.sist.jobgem.service;

import com.sist.jobgem.repository.AlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class SseService {
    private final Map<Integer, SseEmitter> emitters = new ConcurrentHashMap<>();
    private static final Long DEFAULT_TIMEOUT = 600L * 1000 * 60;
    private final AlertRepository alertRepository;

    public SseEmitter subscribe(int userId) {
        SseEmitter emitter = createEmitter(userId);
        sendToClient(userId, "sse 접속 성공 [userid : "+ userId + "]");
        return emitter;
    }

    public void sendToClient(int userId, Object data) {
        SseEmitter emitter = emitters.get(userId);

        if (emitter != null) {
            try {
                System.out.println("Sending message to userId: " + userId + ", message: " + data); // 로그 추가
                emitter.send(SseEmitter.event()
                        .id(String.valueOf(userId))
                        .data(data)
                );
            } catch (Exception e) {
                emitters.remove(userId);
                emitter.completeWithError(e);
            }
        } else {
            System.out.println("No emitter found for userId: " + userId); // 로그 추가
        }
    }

    public SseEmitter createEmitter(int userId) {
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
        emitters.put(userId, emitter);

        emitter.onCompletion(() -> emitters.remove(userId));
        emitter.onTimeout(() -> emitters.remove(userId));

        return emitter;
    }
}
