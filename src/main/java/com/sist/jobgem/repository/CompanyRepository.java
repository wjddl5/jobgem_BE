package com.sist.jobgem.repository;

import com.sist.jobgem.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    @Query("SELECT c FROM Company c JOIN FETCH c.posts p WHERE c.id = :id AND p.poState = :poState")
    Optional<Company> findByIdWithPostsCount(@Param("id") int id, @Param("poState") int poState);
}
