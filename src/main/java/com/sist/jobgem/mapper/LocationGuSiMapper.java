package com.sist.jobgem.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


import com.sist.jobgem.dto.LocationGuSiDto;
import com.sist.jobgem.entity.LocationGuSi;

@Mapper
public interface LocationGuSiMapper {
    LocationGuSiMapper INSTANCE = Mappers.getMapper(LocationGuSiMapper.class);

    LocationGuSi toEntity(LocationGuSiDto locationGuSiDto);

    @Mapping(target = "lgName", source = "lgName")
    LocationGuSiDto toDto(LocationGuSi locationGuSi);

    @Mapping(target = "name", source = "lgName")
    List<LocationGuSiDto> toDtoList(List<LocationGuSi> locationGuSi);

    @Mapping(target = "lgIdx", source = "lgIdx")
    List<LocationGuSi> toEntityList(List<LocationGuSiDto> locationGuSiDtos);
}
