package com.sist.jobgem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sist.jobgem.dto.HireKindDto;
import com.sist.jobgem.entity.HireKind;

import java.util.List;
@Mapper
public interface HireKindMapper {
    HireKindMapper INSTANCE = Mappers.getMapper(HireKindMapper.class);

    HireKind toEntity(HireKindDto hireKindDto);

    HireKindDto toDto(HireKind hireKind);

    List<HireKindDto> toDtoList(List<HireKind> hireKind);

    List<HireKind> toEntityList(List<HireKindDto> hireKindDtos);
}
