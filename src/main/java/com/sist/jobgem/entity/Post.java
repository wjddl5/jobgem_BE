package com.sist.jobgem.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "po_idx", nullable = false)
    private Integer id;

    @Column(name = "co_idx", nullable = false)
    private Integer coIdx;

    @Column(name = "po_title", nullable = false, length = 50)
    private String poTitle;

    @Column(name = "po_content", nullable = false, length = 300)
    private String poContent;

    @Column(name = "po_date", nullable = false)
    private LocalDate poDate;

    @Column(name = "po_deadline")
    private LocalDate poDeadline;

    @Column(name = "po_imgUrl", length = 100)
    private String poImgurl;

    @Column(name = "po_sal", length = 10)
    private String poSal;

    @Column(name = "po_workhour", length = 15)
    private String poWorkhour;

    @Column(name = "po_sub_type", length = 20)
    private String poSubType;

    @Column(name = "po_addr", length = 20)
    private String poAddr;

    @Column(name = "po_email", length = 30)
    private String poEmail;

    @Column(name = "po_fax", length = 30)
    private String poFax;

    @Column(name = "po_state", nullable = false)
    private Integer poState;

}