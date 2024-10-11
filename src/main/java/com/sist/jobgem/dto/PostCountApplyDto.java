package com.sist.jobgem.dto;

import java.time.LocalDate;

import com.sist.jobgem.entity.Company;
import com.sist.jobgem.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class PostCountApplyDto {
    // 필드 선언
    private Integer id;
    private Integer coIdx;
    private String poTitle;
    private String poContent;
    private LocalDate poDate;
    private LocalDate poDeadline;
    private String poSal;
    private String poSubType;
    private String poAddr;
    private String poEmail; 
    private String poFax;
    private Integer poState;
    private Integer applyCount;
    private Company company;


public PostCountApplyDto(Integer id, Integer coIdx, String poTitle, String poContent, LocalDate poDate, LocalDate poDeadline, String poSal, String poSubType, String poAddr, String poEmail, String poFax, Integer poState, Integer applyCount, Company company) {
    this.id = id;
    this.coIdx = coIdx;
    this.poTitle = poTitle;
    this.poContent = poContent;
    this.poDate = poDate;
    this.poDeadline = poDeadline;
    this.poSal = poSal;
    this.poSubType = poSubType;
    this.poAddr = poAddr;
    this.poEmail = poEmail;
    this.poFax = poFax;
    this.poState = poState;
    this.applyCount = applyCount;
    this.company = company;

}
}