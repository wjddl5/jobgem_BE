package com.sist.jobgem.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sist.jobgem.entity.Scrap;

@SpringBootTest
public class ScrapRepositoryTest {

    @Autowired
    private ScrapRepository scrapRepository;

    @Test
    public void testFindByJoIdx() {
        List<Scrap> scraps = scrapRepository.findByJoIdx(1);

        for (Scrap scrap : scraps) {
            System.out.println(scrap.getScDate());
        }
    }
}
