package com.sist.jobgem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sist.jobgem.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>, CompanyRepositoryCustom {

    Optional<Company> findByUser_Id(int id);

    Optional<Company> findByCoNameAndCoNumber(String coName, String coNumber);

}
