package com.sist.jobgem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @Column(name = "of_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "co_idx", nullable = false)
    private Integer coIdx;

    @NotNull
    @Column(name = "jo_idx", nullable = false)
    private Integer joIdx;

    @Size(max = 500)
    @Column(name = "of_content", length = 500)
    private String ofContent;

    @Column(name = "of_type")
    private Integer ofType;

    @Column(name = "of_date")
    private LocalDate ofDate;

    @Column(name = "of_state")
    private Integer ofState;

}