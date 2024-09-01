package com.sist.jobgem.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PostDto {
    Integer id;
    @NotNull
    Integer coIdx;
    @NotNull
    @Size(max = 50)
    String poTitle;
    @NotNull
    @Size(max = 300)
    String poContent;
    @NotNull
    LocalDate poDate;
    LocalDate poDeadline;
    @Size(max = 10)
    String poEdu;
    @Size(max = 30)
    String poImgurl;
    @NotNull
    @Size(max = 10)
    String poLocation;
    @Size(max = 10)
    String poSal;
    @Size(max = 30)
    String poPrefer;
    @Size(max = 2)
    String poCareer;
    @Size(max = 10)
    String poType;
    @Size(max = 15)
    String poWorkhour;
    Integer poState;
    @Size(max = 20)
    String poSubType;
    @Size(max = 20)
    String poAddr;
    @Size(max = 30)
    String poEmail;
    @Size(max = 30)
    String poFax;
}