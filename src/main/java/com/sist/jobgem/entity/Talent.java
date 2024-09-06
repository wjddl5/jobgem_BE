package com.sist.jobgem.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "talents")
public class Talent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ta_idx", nullable = false)
    private Integer id;

    @Column(name = "jo_idx", nullable = false)
    private Integer joIdx;

    @Column(name = "co_idx", nullable = false)
    private Integer coIdx;

    @Column(name = "ta_date", nullable = false)
    private LocalDate taDate;

    // 엔티티가 처음 저장될 때 현재 날짜 설정
    @PrePersist
    @PreUpdate
    public void prePersist() {
        this.taDate = LocalDate.now();  // 업데이트 시 현재 날짜 설정
    }

    @ManyToOne
    @JoinColumn(name = "co_idx", insertable = false, updatable = false)
    private Company company;

    @OneToOne
    @JoinColumn(name = "jo_idx", insertable = false, updatable = false)
    private Jobseeker jobseeker;

}