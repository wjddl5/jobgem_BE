package com.sist.jobgem.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.CompanyDto;
import com.sist.jobgem.dto.JobseekerDto;
import com.sist.jobgem.dto.UserDto;
import com.sist.jobgem.entity.User;
import com.sist.jobgem.mapper.UserMapper;
import com.sist.jobgem.repository.CompanyRepository;
import com.sist.jobgem.repository.JobseekerRepository;
import com.sist.jobgem.repository.UserRepository;

@Service
public class UserService {
    
    private final int USER_TYPE_JOBSEEKER = 1;
    private final int USER_TYPE_COMPANY = 2;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JobseekerRepository jobseekerRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUser(int id) {
        return userRepository.findById(id);
    }

    private int addUser(UserDto userDto, int userType) {
        userDto.setUsPw(passwordEncoder.encode(userDto.getUsPw()));
        userDto.setUsJoinDate(Instant.now());
        userDto.setUsType(userType);

        User userEntity = UserMapper.INSTANCE.toEntity(userDto);
        User addUserResult = userRepository.save(userEntity);

        if(addUserResult != null) {
            return addUserResult.getId();
        }

        return 0;
    }

    public int addJobseeker(UserDto userDto, JobseekerDto jobseekerDto) {
        int userIdx = addUser(userDto, USER_TYPE_JOBSEEKER);
        
        return userIdx;
    }

    public String addCompany(UserDto userDto, CompanyDto companyDto) {
        int userIdx = addUser(userDto, USER_TYPE_COMPANY);
        return "success";
    }
    
}
