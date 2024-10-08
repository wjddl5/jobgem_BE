package com.sist.jobgem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location_gu_si")
public class LocationGuSi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lg_idx", nullable = false)
    private Integer id;

    @Column(name = "ld_idx", nullable = false)
    private Integer ldIdx;

    @Size(max = 20)
    @Column(name = "lg_name", nullable = false, length = 20)
    private String lgName;

    @ManyToOne
    @JoinColumn(name = "ld_idx", referencedColumnName = "ld_idx", insertable = false, updatable = false, nullable = false)
    private LocationDo locationDo;

}