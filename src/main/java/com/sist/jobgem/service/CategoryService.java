package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.AllCategoryResponse;
import com.sist.jobgem.mapper.CareerMapper;
import com.sist.jobgem.mapper.EducationMapper;
import com.sist.jobgem.mapper.HireKindMapper;
import com.sist.jobgem.mapper.LocationDoMapper;
import com.sist.jobgem.mapper.SkillMapper;
import com.sist.jobgem.repository.CareerRepository;
import com.sist.jobgem.repository.EducationRepository;
import com.sist.jobgem.repository.HireKindRepository;
import com.sist.jobgem.repository.LocationDoRepository;
import com.sist.jobgem.repository.LocationGuSiRepository;
import com.sist.jobgem.repository.SkillRepository;

@Service
public class CategoryService {

    @Autowired
    private LocationDoRepository locationDoRepository;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private HireKindRepository hireKindRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private LocationGuSiRepository locationGuSiRepository;

    public AllCategoryResponse getAll() {
        AllCategoryResponse response = new AllCategoryResponse();

        response.setLocationList(LocationDoMapper.INSTANCE.toLocationDtoList(locationDoRepository.findAll()));
        response.setEducationList(EducationMapper.INSTANCE.toDtoList(educationRepository.findAll()));
        response.setCareerList(CareerMapper.INSTANCE.toDtoList(careerRepository.findAll()));
        response.setHireKindList(HireKindMapper.INSTANCE.toDtoList(hireKindRepository.findAll()));
        response.setSkillList(SkillMapper.INSTANCE.toDtoList(skillRepository.findAll()));

        return response;
    }
}
