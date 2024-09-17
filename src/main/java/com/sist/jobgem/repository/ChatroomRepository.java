package com.sist.jobgem.repository;

import com.sist.jobgem.dto.ChatroomResponseDto;
import com.sist.jobgem.entity.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Integer> {
    @Query("select new com.sist.jobgem.dto.ChatroomResponseDto(c) from Chatroom c where c.opIdx = :usId or c.jnIdx = :usId and c.cmStatus = 1")
    List<ChatroomResponseDto> findChatList(@Param("usId") int usId);

    @Query("select new com.sist.jobgem.dto.ChatroomResponseDto(c) from Chatroom c where c.opIdx = :opIdx and c.cmStatus = 1 ORDER BY c.id DESC LIMIT 3")
    List<ChatroomResponseDto> findByOpIdx(@Param("opIdx") int coIdx);
}
