package com.sist.jobgem.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "scraps")
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sc_idx", nullable = false)
    private Integer id;

    @Column(name = "po_idx", nullable = false)
    private Integer poIdx;

    @Column(name = "jo_idx", nullable = false)
    private Integer joIdx;

    @Column(name = "sc_date")
    private LocalDate scDate;

    @Column(name = "sc_state", nullable = false)
    private Integer scState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "po_idx", referencedColumnName = "po_idx", insertable = false, updatable = false)
    private Post post;

}