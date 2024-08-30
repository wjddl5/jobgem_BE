package com.sist.jobgem.repository;

import com.sist.jobgem.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
