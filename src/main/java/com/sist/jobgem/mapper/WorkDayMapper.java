package com.sist.jobgem.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sist.jobgem.entity.WorkDay;
import com.sist.jobgem.dto.WorkDayDto;

@Mapper
public interface WorkDayMapper {
    WorkDayMapper INSTANCE = Mappers.getMapper(WorkDayMapper.class);

    WorkDayDto toDto(WorkDay workDay);

    WorkDay toEntity(WorkDayDto workDayDto);

    List<WorkDayDto> toDtoList(List<WorkDay> workDays);

    List<WorkDay> toEntityList(List<WorkDayDto> workDayDtos);
}
