package com.sist.jobgem.repository;

import com.sist.jobgem.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    List<Chat> findByCmIdx(int cmIdx);

    @Modifying
    @Transactional
    @Query("update Chat c set c.chIsRead = 1 where c.cmIdx = :cmIdx and c.usIdx = :usIdx")
    void updateChat(int cmIdx, int usIdx);
}
