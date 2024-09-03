package com.sist.jobgem.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

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

}