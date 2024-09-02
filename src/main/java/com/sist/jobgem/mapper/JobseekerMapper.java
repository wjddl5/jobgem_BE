package com.sist.jobgem.mapper;

import com.sist.jobgem.dto.JobseekerDto;
import com.sist.jobgem.entity.Jobseeker;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JobseekerMapper {
    JobseekerMapper INSTANCE = Mappers.getMapper(JobseekerMapper.class);

    JobseekerDto toDto(Jobseeker jobseeker);

    Jobseeker toEntity(JobseekerDto jobseekerDto);
}
