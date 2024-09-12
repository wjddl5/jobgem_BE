package com.sist.jobgem.dto;

import com.sist.jobgem.entity.Chat;
import com.sist.jobgem.entity.Chatroom;
import com.sist.jobgem.entity.User;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatroomResponseDto {
    private int id;
    private int opIdx;
    private int jnIdx;
    private int cmStatus;

    private User openUser;
    private User joinUser;


    private List<Chat> chatList;

    private ChatroomResponseDto(Chatroom chatroom) {
        this.id = chatroom.getId();
        this.opIdx = chatroom.getOpIdx();
        this.jnIdx = chatroom.getJnIdx();
        this.cmStatus = chatroom.getCmStatus();

        this.openUser = chatroom.getOpenUser();
        this.joinUser = chatroom.getJoinUser();

        this.chatList = chatroom.getChatList();
    }
}
