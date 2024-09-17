package com.sist.jobgem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "chatrooms")
public class Chatroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cm_idx", nullable = false)
    private Integer id;

    @Column(name = "op_idx", nullable = false)
    private Integer opIdx;

    @Column(name = "jn_idx", nullable = false)
    private Integer jnIdx;

    @Column(name = "cm_status", nullable = false)
    private Integer cmStatus;

    // 엔티티가 처음 저장될 때
    @PrePersist
    public void prePersist() {
        this.cmStatus = 1;
    }

    @OneToMany
    @JoinColumn(name = "cm_idx", insertable = false, updatable = false)
    private List<Chat> chatList;

    @OneToOne
    @JoinColumn(name = "op_idx", insertable = false, updatable = false)
    private User openUser;

    @OneToOne
    @JoinColumn(name = "jn_idx", insertable = false, updatable = false)
    private User joinUser;

}