package com.sist.jobgem.dto;

import java.sql.Date;
import java.time.LocalDate;

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
public class PostCountApplyDto {
    // 필드 선언
    private Integer id;
    private Integer coIdx;
    private String poTitle;
    private String poContent;
    private LocalDate poDate;
    private LocalDate poDeadline;
    private String poImgurl;
    private String poSal;
    private String poWorkhour;
    private String poSubType;
    private String poAddr;
    private String poEmail;
    private String poFax;
    private Integer poState;
    private Integer applyCount;
}

