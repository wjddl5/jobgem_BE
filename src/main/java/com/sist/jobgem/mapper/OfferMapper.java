package com.sist.jobgem.mapper;

import com.sist.jobgem.dto.OfferDto;
import com.sist.jobgem.entity.Offer;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OfferMapper {
    OfferMapper INSTANCE = Mappers.getMapper(OfferMapper.class);

    OfferDto toDto(Offer offer);

    Offer toEntity(OfferDto offerDto);
}
