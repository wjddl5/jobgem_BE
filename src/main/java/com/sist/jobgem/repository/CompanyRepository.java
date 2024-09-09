package com.sist.jobgem.repository;

import com.sist.jobgem.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query("SELECT c " +
            "FROM Company c " +
            "WHERE (:type = 'name' AND c.coName LIKE %:value%) OR " +
            "(:type = 'number' AND c.coNumber LIKE %:value%) OR " +
            "(:type = 'address' AND c.coAddress LIKE %:value%) OR " +
            "(:type = 'tel' AND c.coTel LIKE %:value%) OR " +
            "(:type = 'type' AND c.coType LIKE %:value%) OR " +
            "(:type = 'open' AND cast(c.coOpen as string) LIKE %:value%) OR " +
            "(:type = 'employee' AND cast(c.coEmployee as string) LIKE %:value%) OR " +
            "(:type = 'sales' AND c.coSales LIKE %:value%) OR " +
            "(:type = 'score' AND cast(c.coScore as string) LIKE %:value%) OR " +
            "(:type = 'managerName' AND c.coManagerName LIKE %:value%) OR " +
            "(:type = 'managerTel' AND c.coManagerTel LIKE %:value%)")
    Page<Company> findByTypeAndValueContaining(@Param("type") String type, @Param("value") String value, Pageable pageable);
}
