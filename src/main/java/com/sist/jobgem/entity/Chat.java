package com.sist.jobgem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Column(name = "ch_content", nullable = false)
    private String chContent;

    @Column(name = "ch_date", nullable = false)
    private LocalDate chDate;

    // 엔티티가 처음 저장될 때
    @PrePersist
    public void prePersist() {
        this.chDate = LocalDate.now();
    }

}