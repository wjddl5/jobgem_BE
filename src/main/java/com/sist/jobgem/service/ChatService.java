package com.sist.jobgem.service;

import com.sist.jobgem.dto.ChatDto;
import com.sist.jobgem.entity.Chat;
import com.sist.jobgem.mapper.ChatMapper;
import com.sist.jobgem.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    public ChatDto addChat(ChatDto chatDto) {
        return ChatMapper.INSTANCE.toDto(chatRepository.save(ChatMapper.INSTANCE.toEntity(chatDto)));
    }

    public List<ChatDto> getChatList(int cmIdx) {
        return ChatMapper.INSTANCE.toDtoList(chatRepository.findByCmIdx(cmIdx));
    }
}
