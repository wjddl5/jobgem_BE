package com.sist.jobgem.repository;

import com.sist.jobgem.entity.Social;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialRepository extends JpaRepository<Social, Integer> {
    
}
