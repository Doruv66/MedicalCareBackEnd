package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.business.GetReviewUseCase;
import com.medicalcare.medicalcareappointments.domain.review.GetReviewResponse;
import com.medicalcare.medicalcareappointments.domain.review.Review;
import com.medicalcare.medicalcareappointments.exception.NotFoundReviewException;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetReviewUseCaseImpl implements GetReviewUseCase {

    private final ReviewRepository reviewRepository;

    @Transactional
    @Override
    public GetReviewResponse getReview(long id) {
        Optional<Review> review = reviewRepository.findById(id).map(ReviewConverter::convert);
        if(review.isEmpty()) {
            throw new NotFoundReviewException("NOT_FOUND");
        }
        return GetReviewResponse.builder().review(review.get()).build();
    }
}
