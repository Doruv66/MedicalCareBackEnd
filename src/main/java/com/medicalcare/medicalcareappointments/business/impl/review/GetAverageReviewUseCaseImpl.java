package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.business.GetAverageReviewUseCase;
import com.medicalcare.medicalcareappointments.domain.review.GetAverageReviewResponse;
import com.medicalcare.medicalcareappointments.domain.review.Review;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;

@Service
@AllArgsConstructor
public class GetAverageReviewUseCaseImpl implements GetAverageReviewUseCase {

    private ReviewRepository reviewRepository;

    @Transactional
    @Override
    public GetAverageReviewResponse getAverageReview(long doctorId) {
        OptionalDouble averageReview = reviewRepository.findAll().stream()
                .filter(review -> review.getDoctor().getAccountId().equals(doctorId))
                .map(ReviewConverter::convert)
                .mapToDouble(Review::getRating)
                .average();
        if(averageReview.isPresent()){
            return GetAverageReviewResponse.builder()
                    .averageReview(averageReview.getAsDouble())
                    .build();
        }
        return null;
    }
}
