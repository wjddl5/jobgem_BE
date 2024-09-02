package com.sist.jobgem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "career")
public class Career {
    @Id
    @Column(name = "cr_idx", nullable = false)
    private Integer id;

    @Size(max = 30)
    @Column(name = "cr_name", length = 30)
    private String crName;

}