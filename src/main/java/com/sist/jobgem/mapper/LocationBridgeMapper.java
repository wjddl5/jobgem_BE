package com.sist.jobgem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sist.jobgem.dto.LocationBridgeDto;
import com.sist.jobgem.entity.LocationBridge;


@Mapper
public interface LocationBridgeMapper {
    LocationBridgeMapper INSTANCE = Mappers.getMapper(LocationBridgeMapper.class);
    LocationBridgeDto toDto(LocationBridge locationBridge);
    LocationBridge toEntity(LocationBridgeDto locationBridgeDto);
}
