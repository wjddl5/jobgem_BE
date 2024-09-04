package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sist.jobgem.dto.CompanyDto;
import com.sist.jobgem.dto.ReviewDto;
import com.sist.jobgem.entity.Company;
import com.sist.jobgem.entity.Review;
import com.sist.jobgem.mapper.CompanyMapper;
import com.sist.jobgem.mapper.ReviewMapper;
import com.sist.jobgem.repository.CompanyRepository;
import com.sist.jobgem.repository.ReviewRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    CompanyRepository companyRepository;

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

    public List<CompanyDto> getCompanyList() {
        List<Company> list = companyRepository.findAll();
        return CompanyMapper.INSTANCE.toDtoList(list);
    }

    public Review addReview(ReviewDto review) {
        review.setReState(1);
        return reviewRepository.save(ReviewMapper.INSTANCE.toEntity(review));
    }

    public ReviewDto getReview(int id) {
        // 1. Find the review by ID
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));

        // 2. Convert the review entity to a DTO
        return ReviewMapper.INSTANCE.toDto(review);
    }

    public Review updateReview(ReviewDto reviewDto) {
        // 1. Find the existing review by ID
        Review existingReview = reviewRepository.findById(reviewDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));

        // 2. Update the fields of the existing review
        // Note: Ensure that the entity has appropriate setters or modify via builder
        Review updatedReview = Review.builder()
                .id(existingReview.getId())
                .joIdx(reviewDto.getJoIdx())
                .coIdx(reviewDto.getCoIdx())
                .reTitle(reviewDto.getReTitle())
                .reContent(reviewDto.getReContent())
                .reScore(reviewDto.getReScore())
                .reWriteDate(existingReview.getReWriteDate()) // Preserve the original write date
                .reState(1)
                .company(existingReview.getCompany()) // Preserving the original relationship
                .build();

        // 3. Save the updated review
        return reviewRepository.save(updatedReview);
    }
}