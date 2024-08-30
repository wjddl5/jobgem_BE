package com.sist.jobgem.mapper;

import com.sist.jobgem.dto.CompanyDto;
import com.sist.jobgem.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    CompanyDto toDto(Company company);
    Company toEntity(CompanyDto companyDto);
}
