package com.sist.jobgem.service;

import com.sist.jobgem.dto.ChatroomResponseDto;
import com.sist.jobgem.repository.ChatroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatroomService {
    @Autowired
    private ChatroomRepository chatroomRepository;

    public List<ChatroomResponseDto> getChatroomList(int id) {
        return chatroomRepository.findChatList(id);
    }
}
