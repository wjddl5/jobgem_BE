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
@Table(name = "resumes")
public class Resume {
    @Id
    @Column(name = "re_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "jo_idx", nullable = false)
    private Integer joIdx;

    @Size(max = 50)
    @NotNull
    @Column(name = "re_title", nullable = false, length = 50)
    private String reTitle;

    @Size(max = 100)
    @NotNull
    @Column(name = "re_content", nullable = false, length = 100)
    private String reContent;

    @Size(max = 30)
    @Column(name = "re_fileUrl", length = 30)
    private String reFileurl;

    @NotNull
    @Column(name = "re_state", nullable = false)
    private Integer reState;

}