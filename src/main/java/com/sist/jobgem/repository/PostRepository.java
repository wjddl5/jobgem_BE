package com.sist.jobgem.repository;

import com.sist.jobgem.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    int countByCoIdxAndPoState(int coIdx, int poState);
}
