package com.sist.jobgem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sist.jobgem.dto.SkillDto;
import com.sist.jobgem.entity.Skill;

import java.util.List;
@Mapper
public interface SkillMapper {
    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);

    Skill toEntity(SkillDto skillDto);

    SkillDto toDto(Skill skill);

    List<SkillDto> toDtoList(List<Skill> skill);

    List<Skill> toEntityList(List<SkillDto> skillDtos);
}
