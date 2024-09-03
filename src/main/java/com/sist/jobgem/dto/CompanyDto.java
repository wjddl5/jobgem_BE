package com.sist.jobgem.dto;


import java.time.LocalDate;
import java.util.List;

import com.sist.jobgem.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    Integer coScore;
    Integer coState;

    private User user;
    List<PostDto> posts;
}