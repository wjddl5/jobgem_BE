package com.sist.jobgem.mapper;

import com.sist.jobgem.dto.ResumeDto;
import com.sist.jobgem.entity.Resume;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResumeMapper {
    ResumeMapper INSTANCE = Mappers.getMapper(ResumeMapper.class);

    ResumeDto toDto(Resume resume);

    Resume toEntity(ResumeDto resumeDto);
}
