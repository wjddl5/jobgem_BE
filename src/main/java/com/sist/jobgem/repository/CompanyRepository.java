package com.sist.jobgem.repository;

import com.sist.jobgem.entity.Company;
import com.sist.jobgem.entity.Jobseeker;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query("SELECT c " +
            "FROM Company c " +
            "WHERE c.coName LIKE %:value% OR c.coNumber LIKE %:value% OR " +
            "c.coAddress LIKE %:value% OR c.coTel LIKE %:value% OR " +
            "c.coType LIKE %:value% OR " +
            "cast(c.coOpen as string) LIKE %:value% OR " +
            "cast(c.coEmployee as string) LIKE %:value% OR c.coSales LIKE %:value% OR " +
            "cast(c.coScore as string) LIKE %:value% OR c.coManagerName LIKE %:value% OR " +
            "c.coManagerTel LIKE %:value%")
    Page<Company> findByValueContaining(@Param("value") String value, Pageable pageable);
}
