package com.sist.jobgem.repository;

import com.sist.jobgem.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Integer> {
  @Modifying
  @Query("UPDATE Education e SET e.edName = :editItemName WHERE e.id = :id")
  int updateEdu(@Param("id") int id, @Param("editItemName") String editItemName);

  @Query("SELECT 1 FROM Education e WHERE e.edName = :itemName")
  Integer findByEdName(@Param("itemName") String itemName);
}