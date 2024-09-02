package com.sist.jobgem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "chats")
public class Chat {
    @Id
    @Column(name = "ch_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "us_idx", nullable = false)
    private Integer usIdx;

    @NotNull
    @Column(name = "cm_idx", nullable = false)
    private Integer cmIdx;

    @Size(max = 100)
    @NotNull
    @Column(name = "ch_content", nullable = false, length = 100)
    private String chContent;

}