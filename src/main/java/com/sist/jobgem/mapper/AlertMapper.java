package com.sist.jobgem.mapper;

import com.sist.jobgem.dto.AlertDto;
import com.sist.jobgem.dto.AlertRequestDto;
import com.sist.jobgem.dto.AlertResponseDto;
import com.sist.jobgem.entity.Alert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AlertMapper {
    AlertMapper INSTANCE = Mappers.getMapper(AlertMapper.class);

    AlertResponseDto toDto(Alert entity);

    Alert toEntity(AlertRequestDto dto);
    Alert toEntity(AlertResponseDto dto);

    List<AlertResponseDto> toDto(List<Alert> list);
}
