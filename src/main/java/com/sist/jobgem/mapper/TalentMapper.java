package com.sist.jobgem.mapper;

import com.sist.jobgem.dto.TalentDto;
import com.sist.jobgem.entity.Talent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TalentMapper {
    TalentMapper INSTANCE = Mappers.getMapper(TalentMapper.class);

    TalentDto toDto(Talent entity);

    Talent toEntity(TalentDto dto);
}
