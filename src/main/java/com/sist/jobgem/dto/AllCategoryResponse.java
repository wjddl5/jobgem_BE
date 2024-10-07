package com.sist.jobgem.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AllCategoryResponse {
    
    private List<LocationDto> locationList;
    private List<EducationDto> educationList;
    private List<CareerDto> careerList;
    private List<HireKindDto> hireKindList;
    private List<SkillDto> skillList;
}
