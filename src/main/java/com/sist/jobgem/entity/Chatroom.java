package com.sist.jobgem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "chatroom")
public class Chatroom {
    @Id
    @Column(name = "cm_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "op_idx", nullable = false)
    private Integer opIdx;

    @NotNull
    @Column(name = "jn_idx", nullable = false)
    private Integer jnIdx;

    @Column(name = "Field")
    private Integer field;

}