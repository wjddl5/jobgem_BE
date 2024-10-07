package com.sist.jobgem.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "location_do")
public class LocationDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ld_idx", nullable = false)
    private Integer id;

    @Column(name = "ld_name", length = 50)
    private String ldName;

    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "locationDo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<LocationGuSi> locationGuSi;
}