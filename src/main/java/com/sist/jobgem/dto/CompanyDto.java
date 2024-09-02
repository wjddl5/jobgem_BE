package com.sist.jobgem.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CompanyDto {
    Integer id;
    @NotNull
    Integer usIdx;
    @NotNull
    @Size(max = 20)
    String coName;
    @NotNull
    @Size(max = 15)
    String coNumber;
    @NotNull
    @Size(max = 30)
    String coAddress;
    @NotNull
    @Size(max = 15)
    String coTel;
    @Size(max = 4)
    String coType;
    @NotNull
    Integer coEmployee;
    @NotNull
    @Size(max = 20)
    String coImgUrl;
    @NotNull
    @Size(max = 20)
    String coThumbimgUrl;
    @NotNull
    @Size(max = 20)
    String coSales;
    LocalDate coOpen;
    Integer coScore;
    Integer coState;

    List<PostDto> posts;

}