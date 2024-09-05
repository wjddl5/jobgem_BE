package com.sist.jobgem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

import com.sist.jobgem.dto.EducationDto;
import com.sist.jobgem.entity.Education;

@Mapper
public interface EducationMapper {

  EducationMapper INSTANCE = Mappers.getMapper(EducationMapper.class);

  Education toEntity(EducationDto eDto);

  List<EducationDto> toDto(List<Education> dList);

}