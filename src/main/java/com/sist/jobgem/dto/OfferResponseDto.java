package com.sist.jobgem.dto;

import com.sist.jobgem.entity.Chat;
import com.sist.jobgem.entity.Chatroom;
import com.sist.jobgem.entity.Offer;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferResponseDto {
    private Offer offer;
    private Chatroom chatroom;
    private Chat chat;
}
