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
@Table(name = "posts")
public class Post {
    @Id
    @Column(name = "po_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "co_idx", nullable = false)
    private Integer coIdx;

    @Size(max = 50)
    @NotNull
    @Column(name = "po_title", nullable = false, length = 50)
    private String poTitle;

    @Size(max = 300)
    @NotNull
    @Column(name = "po_content", nullable = false, length = 300)
    private String poContent;

    @NotNull
    @Column(name = "po_date", nullable = false)
    private LocalDate poDate;

    @Column(name = "po_deadline")
    private LocalDate poDeadline;

    @Size(max = 100)
    @Column(name = "po_imgUrl", length = 100)
    private String poImgurl;

    @Size(max = 10)
    @Column(name = "po_sal", length = 10)
    private String poSal;

    @Size(max = 15)
    @Column(name = "po_workhour", length = 15)
    private String poWorkhour;

    @Size(max = 20)
    @Column(name = "po_sub_type", length = 20)
    private String poSubType;

    @Size(max = 20)
    @Column(name = "po_addr", length = 20)
    private String poAddr;

    @Size(max = 30)
    @Column(name = "po_email", length = 30)
    private String poEmail;

    @Size(max = 30)
    @Column(name = "po_fax", length = 30)
    private String poFax;

    @NotNull
    @Column(name = "po_state", nullable = false)
    private Integer poState;

}