package com.sist.jobgem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.jobgem.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);
    Optional<User> findByUsId(String usId);

    @Modifying
    @Query("UPDATE User u SET u.usState = 1 WHERE u.id IN(SELECT j.user.id FROM Jobseeker j WHERE j.id = :joIdx)")
    void updateUserStateByBlockJobseeker(@Param("joIdx") int joIdx);

    @Modifying
    @Query("UPDATE User u SET u.usState = 1 WHERE u.id IN(SELECT c.usIdx FROM Company c WHERE c.id = :coIdx)")
    void updateUserStateByBlockCompany(@Param("coIdx") int coIdx);
}
