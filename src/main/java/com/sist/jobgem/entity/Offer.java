package com.sist.jobgem.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "of_idx", nullable = false)
    private Integer id;

    @Column(name = "co_idx", nullable = false)
    private Integer coIdx;

    @Column(name = "jo_idx", nullable = false)
    private Integer joIdx;

    @Column(name = "of_content", length = 500)
    private String ofContent;

    @Column(name = "of_type")
    private Integer ofType;

    @Column(name = "of_date")
    private LocalDate ofDate;

    @Column(name = "of_state")
    private Integer ofState;

    @ManyToOne
    @JoinColumn(name = "co_idx", insertable = false, updatable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "jo_idx", insertable = false, updatable = false)
    private Jobseeker jobseeker;

    // 엔티티가 처음 저장될 때
    @PrePersist
    public void prePersist() {
        this.ofDate = LocalDate.now();  // 업데이트 시 현재 날짜 설정
        this.ofState = 1;
        this.ofType = 1;
    }

}