package com.sist.jobgem.entity;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicInsert
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "re_idx", nullable = false)
    private Integer id;

    @Column(name = "jo_idx", nullable = false)
    private Integer joIdx;

    @Column(name = "co_idx", nullable = false)
    private Integer coIdx;

    @Column(name = "re_title", nullable = false, length = 20)
    private String reTitle;

    @Lob
    @Column(name = "re_content", nullable = false)
    private String reContent;

    @Column(name = "re_score", nullable = false)
    private Integer reScore;

    @Column(name = "re_write_date", nullable = false)
    private LocalDate reWriteDate;

    @PrePersist
    protected void onCreate() {
        if (this.reWriteDate == null) {
            this.reWriteDate = LocalDate.now();
        }
    }

    @ColumnDefault("1")
    @Column(name = "re_state", nullable = false)
    private Integer reState;

    @ManyToOne
    @JoinColumn(name = "co_idx", insertable = false, updatable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "jo_idx", insertable = false, updatable = false)
    private Jobseeker jobseeker;
}
