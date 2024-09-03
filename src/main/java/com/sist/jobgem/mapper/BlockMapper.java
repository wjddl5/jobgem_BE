package com.sist.jobgem.mapper;

import com.sist.jobgem.dto.BlockDto;
import com.sist.jobgem.entity.Block;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BlockMapper {
    BlockMapper INSTANCE = Mappers.getMapper(BlockMapper.class);

    BlockDto toDto(Block entity);

    Block ToEntity(BlockDto dto);

    List<BlockDto> toDtoList(List<Block> list);
}
