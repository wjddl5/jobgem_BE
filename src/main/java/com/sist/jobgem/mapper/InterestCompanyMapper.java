package com.sist.jobgem.mapper;

import com.sist.jobgem.dto.InterestCompanyDto;
import com.sist.jobgem.dto.InterviewDto;
import com.sist.jobgem.entity.InterestCompany;
import com.sist.jobgem.entity.Interview;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface InterestCompanyMapper{
    InterestCompanyMapper INSTANCE = Mappers.getMapper(InterestCompanyMapper.class);

    InterestCompanyDto toDto(InterestCompany interestCompany);

    InterestCompany toEntity(InterestCompanyDto interestCompanyDto);

}
