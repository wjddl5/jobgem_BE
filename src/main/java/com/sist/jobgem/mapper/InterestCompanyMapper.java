package com.sist.jobgem.mapper;

import java.util.List;

import com.sist.jobgem.dto.CompanyDto;
import com.sist.jobgem.dto.InterestCompanyDto;
import com.sist.jobgem.dto.InterestCompanyResponse;
import com.sist.jobgem.entity.Company;
import com.sist.jobgem.entity.InterestCompany;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface InterestCompanyMapper{
    InterestCompanyMapper INSTANCE = Mappers.getMapper(InterestCompanyMapper.class);

    // 기존 Dto를 위한 매핑
    InterestCompanyDto toDto(InterestCompany interestCompany);

    InterestCompany toEntity(InterestCompanyDto interestCompanyDto);

    // 상속받아 companyDto를 추가한 Dto를 위한 매핑
    CompanyDto toCompanyDto(Company company);
    
    @Mapping(source = "company", target = "companyDto")
    InterestCompanyResponse toInterestCompanyResponse(InterestCompany interestCompany);

    List<InterestCompanyResponse> toInterestCompanyResponseList(List<InterestCompany> interestCompany);
}
