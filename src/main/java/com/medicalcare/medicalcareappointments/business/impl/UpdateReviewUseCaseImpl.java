package com.medicalcare.medicalcareappointments.business.impl;

import com.medicalcare.medicalcareappointments.business.UpdateReviewUseCase;
import com.medicalcare.medicalcareappointments.domain.Review;
import com.medicalcare.medicalcareappointments.domain.UpdateReviewRequest;
import com.medicalcare.medicalcareappointments.domain.UpdateReviewResponse;
import com.medicalcare.medicalcareappointments.exception.InvalidReviewException;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateReviewUseCaseImpl implements UpdateReviewUseCase {

    private final ReviewRepository reviewRepository;

    @Override
    public UpdateReviewResponse updateReview(UpdateReviewRequest request, long id) {
        Optional<ReviewEntity> optionalReview = reviewRepository.findById(id);
        if(optionalReview.isEmpty()){
            throw new InvalidReviewException("REVIEW_IS_INVALID");
        }
        ReviewEntity review = optionalReview.get();
        updateFields(request, review);
        return UpdateReviewResponse.builder()
                .id(review.getReviewId())
                .build();
    }

    private void updateFields(UpdateReviewRequest request, ReviewEntity review){
        review.setComment(request.getComment());
        review.setRating(request.getRating());
        review.setDoctorId(request.getDoctorId());
        review.setUserId(request.getUserId());
        reviewRepository.save(review);
    }
}
