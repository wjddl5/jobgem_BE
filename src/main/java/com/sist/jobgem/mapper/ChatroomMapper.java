package com.sist.jobgem.mapper;

import com.sist.jobgem.dto.ChatroomDto;
import com.sist.jobgem.dto.ChatroomResponseDto;
import com.sist.jobgem.entity.Chatroom;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ChatroomMapper {
    ChatroomMapper INSTANCE = Mappers.getMapper(ChatroomMapper.class);

    Chatroom toEntity(ChatroomDto dto);

    ChatroomDto toDto(Chatroom entity);

    List<ChatroomResponseDto> toDtoList(List<Chatroom> list);

}
