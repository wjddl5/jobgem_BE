package com.sist.jobgem.repository;

import com.sist.jobgem.entity.LocationDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDoRepository extends JpaRepository<LocationDo, Integer> {

  @Modifying
  @Query("UPDATE LocationDo l SET l.ldName = :editItemName WHERE l.id = :id")
  int editLoc(@Param("id") int id, @Param("editItemName") String editItemName);

}
