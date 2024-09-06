package com.sist.jobgem.repository;

import com.sist.jobgem.entity.HireKind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HireKindRepository extends JpaRepository<HireKind, Integer> {

    @Modifying
    @Query("UPDATE HireKind h SET h.hkName = :editItemName WHERE h.id = :id")
    int editHir(@Param("id") int id, @Param("editItemName") String editItemName);

    List<HireKind> findByIdIn(List<Integer> hkIdx);
}
