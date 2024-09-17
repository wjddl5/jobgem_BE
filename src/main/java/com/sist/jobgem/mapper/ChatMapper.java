package com.sist.jobgem.mapper;

import com.sist.jobgem.dto.ChatDto;
import com.sist.jobgem.entity.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ChatMapper {
    ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);

    Chat toEntity(ChatDto dto);

    ChatDto toDto(Chat entity);

    List<ChatDto> toDtoList(List<Chat> list);
}
