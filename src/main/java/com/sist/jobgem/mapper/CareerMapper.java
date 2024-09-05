package com.sist.jobgem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sist.jobgem.dto.CareerDto;
import com.sist.jobgem.entity.Career;

import java.util.List;
@Mapper
public interface CareerMapper {
    CareerMapper INSTANCE = Mappers.getMapper(CareerMapper.class);

    Career toEntity(CareerDto careerDto);

    CareerDto toDto(Career career);

    List<CareerDto> toDtoList(List<Career> career);

    List<Career> toEntityList(List<CareerDto> careerDtos);
}
