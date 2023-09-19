package com.medicalcare.medicalcareappointments.business.impl;

import com.medicalcare.medicalcareappointments.business.CreateReviewUseCase;
import com.medicalcare.medicalcareappointments.domain.CreateAccountRequest;
import com.medicalcare.medicalcareappointments.domain.CreateReviewRequest;
import com.medicalcare.medicalcareappointments.domain.CreateReviewResponse;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AccountEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateReviewUseCaseImpl implements CreateReviewUseCase {

    private final ReviewRepository reviewRepository;

    @Override
    public CreateReviewResponse createReview(CreateReviewRequest request) {
        ReviewEntity savedReview = saveNewReview(request);

        return CreateReviewResponse.builder()
                .id(savedReview.getReviewId())
                .build();
    }

    private ReviewEntity saveNewReview(CreateReviewRequest request){
        ReviewEntity newReview = ReviewEntity.builder()
                .rating(request.getRating())
                .comment(request.getComment())
                .userId(request.getUserId())
                .doctorId(request.getDoctorId())
                .build();
        return reviewRepository.save(newReview);
    }
}
