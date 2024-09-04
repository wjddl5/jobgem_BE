package com.sist.jobgem.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sist.jobgem.entity.Jobseeker;
import com.sist.jobgem.entity.User;

@SpringBootTest
public class JobseekerRepositoryTest {

    @Autowired
    JobseekerRepository jobseekerRepository;

    @Test
    @DisplayName("save 테스트")
    public void userSave() {
        User user = User.builder()
            .id(1)
            .usType(2)
            .usId("test@korea.com")
            .build();

        Jobseeker jobseeker = Jobseeker.builder()
            .joName("김유빈")
            .joGender("M")
            .joTel("01056230756")
            .user(user)
            .build();
        
        Jobseeker jobseeker2 = jobseekerRepository.save(jobseeker);

        // assertThat(jobseeker2.getUser().getId()).isEqualTo(jobseeker.getUser().getId());
    }
}
