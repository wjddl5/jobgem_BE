package com.sist.jobgem.dto;

import java.util.List;

import com.sist.jobgem.entity.WorkDay;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkSchedulesDto {
    private Integer id;
    private String wsStartTime;
    private String wsEndTime;
    private Integer poIdx;
    private List<WorkDay> workDays;
}