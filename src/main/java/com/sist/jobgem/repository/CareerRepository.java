package com.sist.jobgem.repository;

import com.sist.jobgem.entity.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends JpaRepository<Career, Integer> {

    @Modifying
    @Query("UPDATE Career c SET c.crName = :editItemName WHERE c.id = :id")
    int updateCar(@Param("id") int id, @Param("editItemName") String editItemName);

    @Query("SELECT 1 FROM Career c WHERE c.crName = :itemName")
    Integer findByCrName(@Param("itemName") String itemName);
}
