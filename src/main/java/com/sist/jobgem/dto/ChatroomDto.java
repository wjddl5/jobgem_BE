package com.sist.jobgem.dto;

import com.sist.jobgem.entity.Chat;
import com.sist.jobgem.entity.Chatroom;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatroomDto {
    private int id;
    private int opIdx;
    private int jnIdx;
    private int cmStatus;

    private ChatroomDto(Chatroom chatroom) {
        this.id = chatroom.getId();
        this.opIdx = chatroom.getOpIdx();
        this.jnIdx = chatroom.getJnIdx();
        this.cmStatus = chatroom.getCmStatus();
    }
}
