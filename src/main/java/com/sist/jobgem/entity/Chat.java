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
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ch_idx", nullable = false)
    private Integer id;

    @Column(name = "us_idx", nullable = false)
    private Integer usIdx;

    @Column(name = "cm_idx", nullable = false)
    private Integer cmIdx;

    @Column(name = "ch_content", nullable = false, length = 100)
    private String chContent;

}