package com.sist.jobgem.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sist.jobgem.entity.InterestCompany;

@SpringBootTest
public class InterestCompanyRepositoryTest {

    @Autowired
    private InterestCompanyRepository interestCompanyRepository;

    @Test
    public void testFindByJoIdx() {
        List<InterestCompany> interestCompanies = interestCompanyRepository.findByJoIdx(1);
        System.out.println(interestCompanies.size());
    }
}
