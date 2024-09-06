package com.sist.jobgem.entity;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicInsert
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "re_file_url", length = 30)
    private String reFileUrl;

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

}