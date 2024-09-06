package com.sist.jobgem.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sist.jobgem.dto.WorkSchedulesDto;
import com.sist.jobgem.entity.WorkSchedules;

@Mapper
    public interface WorkSchedulesMapper {
    WorkSchedulesMapper INSTANCE = Mappers.getMapper(WorkSchedulesMapper.class);

    WorkSchedulesDto toDto(WorkSchedules workSchedules);
    WorkSchedules toEntity(WorkSchedulesDto workSchedulesDto);

    List<WorkSchedulesDto> toDtoList(List<WorkSchedules> workSchedules);
    List<WorkSchedules> toEntityList(List<WorkSchedulesDto> workSchedulesDto);

}
