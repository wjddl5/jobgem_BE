package com.sist.jobgem.service;

import com.sist.jobgem.dto.ChatDto;
import com.sist.jobgem.dto.ChatRequestDto;
import com.sist.jobgem.mapper.ChatMapper;
import com.sist.jobgem.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    public void addChat(ChatRequestDto chatRequestDto) {
        ChatDto chatDto = ChatDto.builder()
                .usIdx(chatRequestDto.getUsIdx())
                .cmIdx(chatRequestDto.getCmIdx())
                .chDate(LocalDate.parse(chatRequestDto.getChDate()))
                .chContent(chatRequestDto.getChContent())
                .build();
        ChatMapper.INSTANCE.toDto(chatRepository.save(ChatMapper.INSTANCE.toEntity(chatDto)));
    }

    public List<ChatDto> getChatList(int cmIdx) {
        return ChatMapper.INSTANCE.toDtoList(chatRepository.findByCmIdx(cmIdx));
    }
}
