package com.sist.jobgem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "chatrooms")
public class Chatroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cm_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "op_idx", nullable = false)
    private Integer opIdx;

    @NotNull
    @Column(name = "jn_idx", nullable = false)
    private Integer jnIdx;

    @NotNull
    @Column(name = "cm_status", nullable = false)
    private Integer cmStatus;

}