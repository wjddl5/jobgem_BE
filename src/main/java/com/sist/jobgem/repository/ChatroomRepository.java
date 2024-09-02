package com.sist.jobgem.repository;

import com.sist.jobgem.entity.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Integer> {
    
}
