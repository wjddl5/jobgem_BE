package com.sist.jobgem.controller;

import com.sist.jobgem.dto.ChatRequestDto;
import com.sist.jobgem.service.ChatService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChatControllerTest {
    @Autowired
    ChatController chatController;

    @Autowired
    ChatService chatService;

    @Test
    @DisplayName("채팅 저장 테스트")
    void addChat(){
        ChatRequestDto chatRequestDto = new ChatRequestDto(1, 10, "안녕하세요", 0, "2022-10-10");

        chatService.addChat(chatRequestDto);
    }

    @Test
    @DisplayName("두번째 조회일때 캐쉬에서 불러오는지 속도 확인")
    void getChatList(){
        long start1 = System.currentTimeMillis();
        System.out.println(chatController.getChatList(10, 1).toString());
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);

        long start2 = System.currentTimeMillis();
        System.out.println(chatController.getChatList(10, 1).toString());
        long end2 = System.currentTimeMillis();
        System.out.println(end2- start2);
    }

}