package com.sist.jobgem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "posts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Size(max = 10)
    @Column(name = "po_edu", length = 10)
    private String poEdu;

    @Size(max = 30)
    @Column(name = "po_imgUrl", length = 30)
    private String poImgurl;

    @Size(max = 10)
    @NotNull
    @Column(name = "po_location", nullable = false, length = 10)
    private String poLocation;

    @Size(max = 10)
    @Column(name = "po_sal", length = 10)
    private String poSal;

    @Size(max = 30)
    @Column(name = "po_prefer", length = 30)
    private String poPrefer;

    @Size(max = 2)
    @Column(name = "po_career", length = 2)
    private String poCareer;

    @Size(max = 10)
    @Column(name = "po_type", length = 10)
    private String poType;

    @Size(max = 15)
    @Column(name = "po_workhour", length = 15)
    private String poWorkhour;

    @Column(name = "po_state")
    private Integer poState;

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

}