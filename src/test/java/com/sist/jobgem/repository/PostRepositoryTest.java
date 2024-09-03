package com.sist.jobgem.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void postCountTest() {

        int count = postRepository.countByCoIdxAndPoState(1, 1);
        Assertions.assertThat(count).isEqualTo(1);
    }
}