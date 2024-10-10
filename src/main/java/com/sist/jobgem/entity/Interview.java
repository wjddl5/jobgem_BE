package com.sist.jobgem.entity;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "interviews")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "in_idx", nullable = false)
    private Integer id;

    @Column(name = "jo_idx", nullable = false)
    private Integer joIdx;

    @Column(name = "co_idx", nullable = false)
    private Integer coIdx;

    @Column(name = "in_content", nullable = false, length = 500)
    private String inContent;

    @Column(name = "in_write_date", nullable = false)
    private LocalDate inWriteDate;

    @PrePersist
    protected void onCreate() {
        if (this.inWriteDate == null) {
            this.inWriteDate = LocalDate.now();
        }
    }

    @Column(name = "in_level")
    private Integer inLevel;

    @Column(name = "in_state")
    private Integer inState;

    @ManyToOne
    @JoinColumn(name = "co_idx", insertable = false, updatable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "jo_idx", insertable = false, updatable = false)
    private Jobseeker jobseeker;

}