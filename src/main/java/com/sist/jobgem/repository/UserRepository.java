package com.sist.jobgem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sist.jobgem.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    User findById(int id);
}
