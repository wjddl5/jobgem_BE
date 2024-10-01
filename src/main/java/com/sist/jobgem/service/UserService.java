package com.sist.jobgem.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.CompanyDto;
import com.sist.jobgem.dto.JobseekerDto;
import com.sist.jobgem.dto.LoginResponse;
import com.sist.jobgem.dto.UserDto;
import com.sist.jobgem.entity.Company;
import com.sist.jobgem.entity.Jobseeker;
import com.sist.jobgem.entity.User;
import com.sist.jobgem.enums.LoginStatusEnum;
import com.sist.jobgem.mapper.CompanyMapper;
import com.sist.jobgem.mapper.JobseekerMapper;
import com.sist.jobgem.mapper.UserMapper;
import com.sist.jobgem.repository.CompanyRepository;
import com.sist.jobgem.repository.JobseekerRepository;
import com.sist.jobgem.repository.UserRepository;
import com.sist.jobgem.util.jwt.AccessTokenClaims;
import com.sist.jobgem.util.jwt.JwtProvider;
import com.sist.jobgem.util.jwt.TokenDto;

@Service
public class UserService {

    private final int USER_TYPE_ADMIN = 0;
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

    @Autowired
    private JwtProvider jwtProvider;

    public User getUser(int id) {
        return userRepository.findById(id);
    }

    public LoginResponse login(String usId, String usPw) {
        Optional<User> user = userRepository.findByUsId(usId);

        if (user.isPresent() && user.get().getUsState() == 1) {
            if (passwordEncoder.matches(usPw, user.get().getUsPw())) {
                return new LoginResponse(UserMapper.INSTANCE.toDto(user.get()),
                        LoginStatusEnum.LOGIN_SUCCESS.getMessage());
            }
            return new LoginResponse(null, LoginStatusEnum.LOGIN_WRONG_PW.getMessage());
        }
        return new LoginResponse(null, LoginStatusEnum.LOGIN_WRONG_EMAIL.getMessage());
    }

    public TokenDto createToken(UserDto userDto) {
        int usIdx = userDto.getId();
        int idx = 0;
        int usType = 0;
        String name = "";
        String img = "";
        String email = userDto.getUsId();

        if (userDto.getUsType() == USER_TYPE_JOBSEEKER) {
            Optional<Jobseeker> jobseeker = jobseekerRepository.findByUser_Id(userDto.getId());
            idx = jobseeker.get().getId();
            name = jobseeker.get().getJoName();
            img = jobseeker.get().getJoImgUrl();
            usType = USER_TYPE_JOBSEEKER;
        } else if (userDto.getUsType() == USER_TYPE_COMPANY) {
            Optional<Company> company = companyRepository.findByUser_Id(userDto.getId());
            idx = company.get().getId();
            name = company.get().getCoName();
            img = company.get().getCoImgUrl();
            usType = USER_TYPE_COMPANY;
        } else {
            idx = userDto.getId();
            name = "관리자";
            usType = USER_TYPE_ADMIN;
        }

        AccessTokenClaims accessTokenClaims = AccessTokenClaims.builder()
                .usIdx(usIdx)
                .idx(idx)
                .email(email)
                .name(name)
                .img(img)
                .role(usType)
                .build();

        TokenDto token = jwtProvider.createToken(accessTokenClaims);
        String refreshToken = token.getRefreshToken();

        userDto.setRefreshToken(refreshToken);

        userRepository.save(UserMapper.INSTANCE.toEntity(userDto));

        return token;
    }

    public boolean isEmailExist(String email) {
        Optional<User> user = userRepository.findByUsId(email);
        return user.isPresent();
    }

    private User joinUser(UserDto userDto, int userType) {
        userDto.setUsPw(passwordEncoder.encode(userDto.getUsPw()));
        userDto.setUsJoinDate(Instant.now());
        userDto.setUsType(userType);

        User userEntity = UserMapper.INSTANCE.toEntity(userDto);
        User joinUserResult = userRepository.save(userEntity);

        if (joinUserResult != null) {
            return joinUserResult;
        }

        return null;
    }

    public int joinJobseeker(UserDto userDto, JobseekerDto jobseekerDto) {
        User user = joinUser(userDto, USER_TYPE_JOBSEEKER);
        jobseekerDto.setUser(user);
        int jobseekerIdx = jobseekerRepository.save(JobseekerMapper.INSTANCE.toEntity(jobseekerDto)).getId();

        return jobseekerIdx;
    }

    public int joinCompany(UserDto userDto, CompanyDto companyDto) {
        User user = joinUser(userDto, USER_TYPE_COMPANY);
        companyDto.setUser(user);
        int companyIdx = companyRepository.save(CompanyMapper.INSTANCE.toEntity(companyDto)).getId();

        return companyIdx;
    }

    public List<UserDto> findAllUsers() {
        return UserMapper.INSTANCE.toDtoList(userRepository.findAll());
    }

    public boolean checkPwd(int id, String chkPw) {
        User user = userRepository.findById(id);
        // .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. ID: " + id));
        if (user != null) {
            String usPw = user.getUsPw();

            return passwordEncoder.matches(chkPw, usPw);
        } else
            return false;
    }

    public boolean updatePwd(int id, String newPwd) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new IllegalArgumentException("해당 사용자의 유저 정보가 없습니다.");
        }
        UserDto uDto = UserMapper.INSTANCE.toDto(user);
        uDto.setUsPw(passwordEncoder.encode(newPwd));

        User updatedUser = UserMapper.INSTANCE.toEntity(uDto);
        userRepository.save(updatedUser);

        return true;
    }
}
