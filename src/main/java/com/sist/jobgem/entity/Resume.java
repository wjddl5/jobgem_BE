package com.sist.jobgem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "resumes")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "re_idx", nullable = false)
    private Integer id;

    @Column(name = "jo_idx", nullable = false)
    private Integer joIdx;

    @Column(name = "re_title", nullable = false, length = 50)
    private String reTitle;

    @Column(name = "re_content", nullable = false, length = 100)
    private String reContent;

    @Column(name = "re_fileUrl", length = 30)
    private String reFileurl;

    @Column(name = "re_state", nullable = false)
    private Integer reState;

}