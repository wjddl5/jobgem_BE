package com.sist.jobgem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import com.sist.jobgem.dto.CompanyDto;
import com.sist.jobgem.dto.ReviewDto;
import com.sist.jobgem.entity.Company;
import com.sist.jobgem.entity.Review;
import com.sist.jobgem.mapper.CompanyMapper;
import com.sist.jobgem.mapper.ReviewMapper;
import com.sist.jobgem.repository.CompanyRepository;
import com.sist.jobgem.repository.ReviewRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    CompanyRepository companyRepository;

    public Page<ReviewDto> getReviewList(int id, Pageable pageable) {

        Page<Review> reviewList = reviewRepository.findByJoIdxAndReState(id, 1, pageable);

        // Review -> ReviewDto 변환
        List<ReviewDto> reviewDtoList = reviewList.getContent().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        // 변환된 DtoList를 사용하여 새로운 Page<ReviewDto> 객체를 생성
        return new PageImpl<>(reviewDtoList, pageable, reviewList.getTotalElements());
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
        Review newReview = reviewRepository.save(ReviewMapper.INSTANCE.toEntity(review));
        updateCoScore(review.getCoIdx());
        return newReview;
    }

    // 리뷰 추가 및 수정시 기업 평균별점 수정
    public void updateCoScore(int coIdx) {
        List<Review> list = reviewRepository.findByCoIdxAndReState(coIdx, 1);
        double sum = 0;
        for (Review r : list) {
            sum += r.getReScore();
        }
        double avg = sum / list.size();
        Optional<Company> company = companyRepository.findById(coIdx);
        company.ifPresent(value -> {
            CompanyDto companyDto = CompanyMapper.INSTANCE.toDto(value);
            companyDto.setCoScore(avg);
            companyRepository.save(CompanyMapper.INSTANCE.toEntity(companyDto));
        });
    }

    public ReviewDto getReview(int id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));

        return ReviewMapper.INSTANCE.toDto(review);
    }

    public Review updateReview(ReviewDto reviewDto) {
        Review existingReview = reviewRepository.findById(reviewDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));

        Review updatedReview = Review.builder()
                .id(existingReview.getId())
                .joIdx(reviewDto.getJoIdx())
                .coIdx(reviewDto.getCoIdx())
                .reTitle(reviewDto.getReTitle())
                .reContent(reviewDto.getReContent())
                .reScore(reviewDto.getReScore())
                .reWriteDate(LocalDate.now())
                .reState(1)
                .company(existingReview.getCompany())
                .build();
        Review newReview = reviewRepository.save(updatedReview);
        updateCoScore(reviewDto.getCoIdx());
        return newReview;
    }

    public int deleteReview(int id) {
        int result = reviewRepository.deleteReview(id);

        Optional<Review> review = reviewRepository.findById(id);
        review.ifPresent(value -> updateCoScore(value.getCoIdx()));
        return result;
    }

    public List<ReviewDto> getReviewListByCoIdx(int coIdx) {
        return ReviewMapper.INSTANCE.toDtoList(reviewRepository.findByCoIdxAndReState(coIdx, 1));
    }

    public Page<ReviewDto> getReviewListByCoIdx(int coIdx, Pageable pageable){
        return reviewRepository.findCompanyReview(coIdx, pageable);
    }

}
