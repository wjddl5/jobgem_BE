package com.sist.jobgem.service;

import com.sist.jobgem.dto.ChatDto;
import com.sist.jobgem.dto.ChatRequestDto;
import com.sist.jobgem.entity.Chatroom;
import com.sist.jobgem.mapper.ChatMapper;
import com.sist.jobgem.repository.ChatRepository;
import com.sist.jobgem.repository.ChatroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private ChatroomRepository chatroomRepository;

    public ChatDto createChat(ChatRequestDto chatRequestDto) {
        ChatDto chatDto = ChatDto.builder()
                .usIdx(chatRequestDto.getUsIdx())
                .cmIdx(chatRequestDto.getCmIdx())
                .chDate(LocalDateTime.now())
                .chContent(chatRequestDto.getChContent())
                .chIsRead(0)
                .build();
        return ChatMapper.INSTANCE.toDto(chatRepository.save(ChatMapper.INSTANCE.toEntity(chatDto)));
    }

    public List<ChatDto> getChatList(int cmIdx, int usIdx) {
        Chatroom chatroom = chatroomRepository.findById(cmIdx).orElseThrow();
        int joinUser = chatroom.getJnIdx();
        int openUser = chatroom.getOpIdx();

        if(usIdx == joinUser) {
            chatRepository.updateChat(cmIdx, openUser);
        }else if(usIdx == openUser) {
            chatRepository.updateChat(cmIdx, joinUser);
        }

        return ChatMapper.INSTANCE.toDtoList(chatRepository.findByCmIdx(cmIdx));
    }

}
