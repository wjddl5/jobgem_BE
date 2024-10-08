package com.sist.jobgem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;

@Getter
@Entity
@Builder
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "po_idx", nullable = false)
    private Integer id;

    @Column(name = "co_idx", nullable = false)
    private Integer coIdx;

    @Column(name = "po_title", nullable = false, length = 50)
    private String poTitle;

    @Column(name = "po_content", nullable = false, columnDefinition = "TEXT")
    private String poContent;

    @Column(name = "po_date", nullable = false)
    private LocalDate poDate;

    @Column(name = "po_deadline")
    private LocalDate poDeadline;

    @Column(name = "po_sal", length = 20)
    private String poSal;

    @Column(name = "ws_start_time")
    private String wsStartTime;

    @Column(name = "ws_end_time")
    private String wsEndTime;

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

    @ManyToOne
    @JoinColumn(name = "co_idx", nullable = false, insertable = false, updatable = false)
    private Company company;

    @ManyToMany
    @JoinTable(name = "education_bridge", joinColumns = @JoinColumn(name = "po_idx"), inverseJoinColumns = @JoinColumn(name = "ed_idx"))
    private List<Education> education;

    @ManyToMany
    @JoinTable(name = "location_bridge", joinColumns = @JoinColumn(name = "po_idx"), inverseJoinColumns = @JoinColumn(name = "lg_idx"))
    private List<LocationGuSi> locationGuSi;

    @ManyToMany
    @JoinTable(name = "hk_bridge", joinColumns = @JoinColumn(name = "po_idx"), inverseJoinColumns = @JoinColumn(name = "hk_idx"))
    private List<HireKind> hireKind;

    @ManyToMany
    @JoinTable(name = "careers_bridge", joinColumns = @JoinColumn(name = "po_idx"), inverseJoinColumns = @JoinColumn(name = "cr_idx"))
    private List<Career> career;

    @ManyToMany
    @JoinTable(name = "skill_bridge", joinColumns = @JoinColumn(name = "po_idx"), inverseJoinColumns = @JoinColumn(name = "sk_idx"))
    private List<Skill> skill;

    @ManyToMany
    @JoinTable(name = "post_schedule_days", joinColumns = @JoinColumn(name = "po_idx"), inverseJoinColumns = @JoinColumn(name = "wd_idx"))
    private List<WorkDay> workDays;
}