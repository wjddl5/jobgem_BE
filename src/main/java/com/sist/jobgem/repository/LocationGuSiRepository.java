package com.sist.jobgem.repository;

import com.sist.jobgem.entity.LocationGuSi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LocationGuSiRepository extends JpaRepository<LocationGuSi, Integer> {
    List<LocationGuSi> findByIdIn(List<Integer> lgIdx);

    @Query("SELECT l FROM LocationGuSi l WHERE l.ldIdx = :ldIdx")
    List<LocationGuSi> findByLdIdx(int ldIdx);

    @Modifying
    @Query("UPDATE LocationGuSi l SET l.lgName = :editItemName WHERE l.id = :id")
    int editLoc(@Param("id") int id, @Param("editItemName") String editItemName);

    @Modifying
    @Query("DELETE FROM LocationGuSi l WHERE l.ldIdx = :id")
    int deleteByLdIdx(@Param("id") int id);
}
