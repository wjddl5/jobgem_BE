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
@Table(name = "comments")
public class Comment {
    @Id
    @Column(name = "comm_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "bo_idx", nullable = false)
    private Integer boIdx;

    @NotNull
    @Column(name = "us_idx", nullable = false)
    private Integer usIdx;

    @Size(max = 100)
    @NotNull
    @Column(name = "comm_content", nullable = false, length = 100)
    private String commContent;

    @NotNull
    @Column(name = "comm_status", nullable = false)
    private Integer commStatus;

}