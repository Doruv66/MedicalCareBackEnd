package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.business.GetReviewUseCase;
import com.medicalcare.medicalcareappointments.domain.review.Review;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetReviewUseCaseImpl implements GetReviewUseCase {

    private final ReviewRepository reviewRepository;

    @Override
    public Optional<Review> getReview(long id) {
        return reviewRepository.findById(id).map(ReviewConverter::convert);
    }
}
