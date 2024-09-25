package com.sist.jobgem.mapper;

import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;
import com.sist.jobgem.dto.ScrapDto;
import com.sist.jobgem.entity.Scrap;

@Mapper
public interface ScrapMapper {
    ScrapMapper INSTANCE = Mappers.getMapper(ScrapMapper.class);
    ScrapDto toDto(Scrap scrap);
    Scrap toEntity(ScrapDto scrapDto);
}
