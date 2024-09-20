package com.sist.jobgem.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sist.jobgem.entity.Career;
import com.sist.jobgem.entity.Education;
import com.sist.jobgem.entity.HireKind;
import com.sist.jobgem.entity.LocationGuSi;
import com.sist.jobgem.entity.Skill;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostWriteDto {
    private Integer id;
    private Integer coIdx;
    private String poTitle;
    private String poContent;
    private String workStartTime;
    private String workEndTime;
    private String poDate;
    private String poDeadline;
    private Integer salary;
    private String subType;
    private String addr;
    private String email;
    private String fax;
    private Integer poState;
    private String startDate;
    private String endDate;
    private String imgUrl;

    private List<EducationDto> education;
    private List<HireKindDto> hireKind;
    private List<CareerDto> career;
    private List<SkillDto> skill;
    private List<LocationGuSiDto> location;
    private List<WorkDayDto> workDay;
}
