package com.sist.jobgem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import com.sist.jobgem.entity.Career;
import com.sist.jobgem.entity.Education;
import com.sist.jobgem.entity.HireKind;
import com.sist.jobgem.entity.LocationGuSi;
import com.sist.jobgem.entity.Skill;
import com.sist.jobgem.mapper.CareerMapper;
import com.sist.jobgem.mapper.EducationMapper;
import com.sist.jobgem.mapper.HireKindMapper;
import com.sist.jobgem.mapper.LocationGuSiMapper;
import com.sist.jobgem.mapper.SkillMapper;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    public PostDto(PostWriteDto postDto) {
        this.coIdx = postDto.getCoIdx();
        this.poTitle = postDto.getPoTitle();
        this.poContent = postDto.getPoContent();
        this.poDate = LocalDate.parse(postDto.getPoDate());
        this.poDeadline = LocalDate.parse(postDto.getPoDeadline());
        this.poSubType = postDto.getSubType();
        this.poAddr = postDto.getAddr();
        this.poEmail = postDto.getEmail();
        this.poFax = postDto.getFax();
        this.poState = postDto.getPoState();
        this.education = EducationMapper.INSTANCE.toEntityList(postDto.getEducation());
        this.locationGuSi = LocationGuSiMapper.INSTANCE.toEntityList(postDto.getLocation());
        this.hireKind = HireKindMapper.INSTANCE.toEntityList(postDto.getHireKind());
        this.career = CareerMapper.INSTANCE.toEntityList(postDto.getCareer());
        this.skill = SkillMapper.INSTANCE.toEntityList(postDto.getSkill());
    }

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

    private List<Education> education;
    private List<LocationGuSi> locationGuSi;
    private List<HireKind> hireKind;
    private List<Career> career;
    private List<Skill> skill;
}