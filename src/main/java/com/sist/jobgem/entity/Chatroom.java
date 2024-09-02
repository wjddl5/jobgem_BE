package com.sist.jobgem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
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

}