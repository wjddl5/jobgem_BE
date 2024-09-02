package com.sist.jobgem.repository;

import com.sist.jobgem.entity.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface blacklistRepository extends JpaRepository<Blacklist, Integer> {
    
}
