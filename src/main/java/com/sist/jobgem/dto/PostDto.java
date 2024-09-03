package com.sist.jobgem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PostDto {
    Integer id;
    Integer coIdx;
    String poTitle;
    String poContent;
    LocalDate poDate;
    LocalDate poDeadline;
    String poEdu;
    String poImgurl;
    String poLocation;
    String poSal;
    String poPrefer;
    String poCareer;
    String poType;
    String poWorkhour;
    Integer poState;
    String poSubType;
    String poAddr;
    String poEmail;
    String poFax;
}