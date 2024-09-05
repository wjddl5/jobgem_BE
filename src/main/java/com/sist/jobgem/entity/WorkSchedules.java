package com.sist.jobgem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.List;

@Entity
@Table(name = "work_schedules")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkSchedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ws_idx")
    private Integer id;
    @Column(name = "ws_start_time")
    private String wsStartTime;

    @Column(name = "ws_end_time")
    private String wsEndTime;

    @Column(name = "po_idx")
    private Integer poIdx;

    @ManyToMany
    @JoinTable(name = "work_schedule_days", joinColumns = @JoinColumn(name = "ws_idx"), inverseJoinColumns = @JoinColumn(name = "wd_idx"))
    private List<WorkDay> workDays;
}
