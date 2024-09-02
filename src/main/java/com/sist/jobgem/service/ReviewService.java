package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.ReviewDto;
import com.sist.jobgem.entity.Review;
import com.sist.jobgem.repository.ReviewRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    public Page<ReviewDto> getReviewList(int id, Pageable pageable) {
        Pageable pageable2 = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));

        Page<Review> reviewList = reviewRepository.findByJoIdx(id, pageable2);

        // Review -> ReviewDto 변환
        List<ReviewDto> reviewDtoList = reviewList.getContent().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        // 변환된 DtoList를 사용하여 새로운 Page<ReviewDto> 객체를 생성
        return new PageImpl<>(reviewDtoList, pageable2, reviewList.getTotalElements());
    }

    // review 엔티티를 reviewDto로 변환하는 메소드
    private ReviewDto convertToDto(Review review) {
        return ReviewDto.fromEntity(review);
    }

}
