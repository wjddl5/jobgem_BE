package com.sist.jobgem.dto;


import java.time.LocalDate;
import java.util.List;

import com.sist.jobgem.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDto {
    Integer id;
    Integer usIdx;
    String coName;
    String coNumber;
    String coAddress;
    String coTel;
    String coType;
    Integer coEmployee;
    String coImgUrl;
    String coThumbimgUrl;
    String coSales;
    LocalDate coOpen;
    Double coScore;
    Integer coState;
    String coManagerName;
    String coManagerTel;

    private User user;
}