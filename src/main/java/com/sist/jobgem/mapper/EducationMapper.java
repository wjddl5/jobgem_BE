package com.sist.jobgem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;
import org.mapstruct.ReportingPolicy;
import com.sist.jobgem.dto.EducationDto;
import com.sist.jobgem.entity.Education;

@Mapper
public interface EducationMapper {
    EducationMapper INSTANCE = Mappers.getMapper(EducationMapper.class);

    Education toEntity(EducationDto educationDto);

    EducationDto toDto(Education education);

    List<EducationDto> toDtoList(List<Education> education);
    
    List<Education> toEntityList(List<EducationDto> educationDtos);
}
