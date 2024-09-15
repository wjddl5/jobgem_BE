package com.sist.jobgem.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecruitRequest {

    private List<Integer> crList;
    private List<Integer> edList;
    private List<Integer> hkList;
    private List<Integer> lgList;
    private List<Integer> skList;

    private int curPage;
}