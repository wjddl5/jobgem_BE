package com.sist.jobgem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "applyments")
public class Applyment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ap_idx", nullable = false)
    private Integer id;

    @Column(name = "po_idx", nullable = false)
    private Integer poIdx;

    @Column(name = "jo_idx", nullable = false)
    private Integer joIdx;

    @Column(name = "re_idx", nullable = false)
    private Integer reIdx;

    @Column(name = "ap_date", nullable = false)
    private LocalDate apDate;

    @PrePersist
    protected void onCreate() {
        if (this.apDate == null) {
            this.apDate = LocalDate.now();
        }
    }

    @Column(name = "ap_read")
    private Integer apRead;

    @Column(name = "ap_state", nullable = false)
    private Integer apState;

    @ManyToOne
    @JoinColumn(name = "po_idx", referencedColumnName = "po_idx", insertable = false, updatable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "jo_idx", referencedColumnName = "jo_idx", insertable = false, updatable = false)
    private Jobseeker jobseeker;
}