package com.sist.jobgem.service;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.CompanyDto;
import com.sist.jobgem.dto.JobseekerDto;
import com.sist.jobgem.dto.UserDto;
import com.sist.jobgem.entity.User;
import com.sist.jobgem.enums.LoginStatusEnum;
import com.sist.jobgem.mapper.CompanyMapper;
import com.sist.jobgem.mapper.JobseekerMapper;
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

    public LoginStatusEnum login(String usId, String usPw) {
        Optional<User> user = userRepository.findByUsId(usId);
        
        if (user.isPresent()) {
            if (passwordEncoder.matches(usPw, user.get().getUsPw())) {
                return LoginStatusEnum.LOGIN_SUCCESS;
            }
            return LoginStatusEnum.LOGIN_WRONG_PW;
        }
        return LoginStatusEnum.LOGIN_WRONG_EMAIL;
    }

    private User addUser(UserDto userDto, int userType) {
        userDto.setUsPw(passwordEncoder.encode(userDto.getUsPw()));
        userDto.setUsJoinDate(Instant.now());
        userDto.setUsType(userType);

        User userEntity = UserMapper.INSTANCE.toEntity(userDto);
        User addUserResult = userRepository.save(userEntity);

        if (addUserResult != null) {
            return addUserResult;
        }

        return null;
    }

    public boolean checkEmail(String email) {
        Optional<User> user = userRepository.findByUsId(email);
        return user.isPresent();
    }

    public int addJobseeker(UserDto userDto, JobseekerDto jobseekerDto) {
        User user = addUser(userDto, USER_TYPE_JOBSEEKER);
        jobseekerDto.setUser(user);
        int jobseekerIdx = jobseekerRepository.save(JobseekerMapper.INSTANCE.toEntity(jobseekerDto)).getId();

        return jobseekerIdx;
    }

    public int addCompany(UserDto userDto, CompanyDto companyDto) {
        User user = addUser(userDto, USER_TYPE_COMPANY);
        companyDto.setUser(user);
        int companyIdx = companyRepository.save(CompanyMapper.INSTANCE.toEntity(companyDto)).getId();

        return companyIdx;
    }
}
