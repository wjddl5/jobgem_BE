package com.sist.jobgem.mapper;

import com.sist.jobgem.dto.InterviewDto;
import com.sist.jobgem.entity.Interview;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InterviewMapper {
    InterviewMapper INSTANCE = Mappers.getMapper(InterviewMapper.class);

    InterviewDto toDto(Interview interview);

    Interview toEntity(InterviewDto interviewDto);
}
