package com.sist.jobgem.mapper;

import com.sist.jobgem.dto.ApplymentDto;
import com.sist.jobgem.entity.Applyment;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApplymentMapper {
    ApplymentMapper INSTANCE = Mappers.getMapper(ApplymentMapper.class);

    ApplymentDto toDto(Applyment applyment);

    Applyment toEntity(ApplymentDto ApplymentDto);
}
