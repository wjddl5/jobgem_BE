package com.sist.jobgem.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.sist.jobgem.dto.LocationDoDto;
import com.sist.jobgem.entity.LocationDo;

@Mapper
public interface LocationDoMapper {
    LocationDoMapper INSTANCE = Mappers.getMapper(LocationDoMapper.class);

    @Mapping(target = "ldName", source = "name")
    LocationDo toEntity(LocationDoDto locationDoDto);

    @Mapping(target = "name", source = "ldName")
    LocationDoDto toDto(LocationDo locationDo);

    @Mapping(target = "name", source = "ldName")
    List<LocationDoDto> toDtoList(List<LocationDo> locationDo);
}
