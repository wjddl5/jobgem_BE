package com.sist.jobgem.mapper;

import com.sist.jobgem.dto.BlackListDto;
import com.sist.jobgem.dto.BlackListRequestDto;
import com.sist.jobgem.dto.BlockDto;
import com.sist.jobgem.entity.Blacklist;
import com.sist.jobgem.entity.Block;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BlacklistMapper {
    BlacklistMapper INSTANCE = Mappers.getMapper(BlacklistMapper.class);

    BlackListDto toDto(Blacklist entity);

    Blacklist toEntity(BlackListRequestDto dto);

    List<BlackListDto> toDtoList(List<Blacklist> list);
}
