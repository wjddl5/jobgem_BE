package com.sist.jobgem.controller;

import com.sist.jobgem.dto.ChatDto;
import com.sist.jobgem.dto.ChatRedisDto;
import com.sist.jobgem.dto.ChatRequestDto;
import com.sist.jobgem.dto.ChatroomResponseDto;
import com.sist.jobgem.service.ChatService;
import com.sist.jobgem.service.ChatroomService;
import com.sist.jobgem.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class ChatController {

    @Autowired
    private SimpMessageSendingOperations template;

    @Autowired
    private ChatroomService chatroomService;
    @Autowired
    private ChatService chatService;
    @Autowired
    private RedisService redisService;


    // 채팅 리스트 반환
    @GetMapping("/chatroom")
    public ResponseEntity<List<ChatroomResponseDto>> getChatMessages(@RequestParam int id) {
        return ResponseEntity.ok(chatroomService.getChatroomList(id));
    }

    // 해당 채팅방의 채팅들
    @GetMapping("/chatroom/{id}/chat")
    public ResponseEntity<List<ChatRedisDto>> getChatList(@PathVariable int id, @RequestParam int usIdx) {
        boolean isRedis = redisService.hasKey("chatroom"+id);
        if(!isRedis){
            List<ChatDto> chatList = chatService.getChatList(id, usIdx);
            for(ChatDto chat : chatList){
                ChatRedisDto chatRedisDto = ChatRedisDto.builder()
                        .usIdx(chat.getUsIdx())
                        .cmIdx(chat.getCmIdx())
                        .chContent(chat.getChContent())
                        .chDate(chat.getChDate().toString())
                        .chIsRead(chat.getChIsRead())
                        .build();
                redisService.createToListWithTTL("chatroom"+id, chatRedisDto, 1, TimeUnit.HOURS);
            }
        }
        return ResponseEntity.ok(redisService.getChatList("chatroom"+id, usIdx));
    }

    //메시지 송신 및 수신, /pub가 생략된 모습. 클라이언트 단에선 /pub/chat 요청
    @MessageMapping("/chat")
    public Object receiveMessage(ChatRequestDto chat) {
        template.convertAndSend("/sub/chatroom/"+chat.getCmIdx(), chat);
        chatService.createChat(chat);
        LocalDateTime utcNow = LocalDateTime.now();
        ZonedDateTime kstNow = utcNow.atZone(ZoneId.of("UTC"))
                .withZoneSameInstant(ZoneId.of("Asia/Seoul"));
        ChatRedisDto chatRedisDto = ChatRedisDto.builder()
                .usIdx(chat.getUsIdx())
                .cmIdx(chat.getCmIdx())
                .chContent(chat.getChContent())
                .chDate(LocalDateTime.now().toString())
                .chIsRead(0)
                .build();
        return redisService.createToListWithTTL("chatroom"+chat.getCmIdx(), chatRedisDto, 1, TimeUnit.HOURS);
    }
}