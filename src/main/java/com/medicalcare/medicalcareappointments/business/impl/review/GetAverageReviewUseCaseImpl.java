package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.business.GetAverageReviewUseCase;
import com.medicalcare.medicalcareappointments.domain.review.GetAverageReviewResponse;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAverageReviewUseCaseImpl implements GetAverageReviewUseCase {

    private ReviewRepository reviewRepository;

    @Transactional
    @Override
    public GetAverageReviewResponse getAverageReview(long doctorId) {
        Double averageReview = reviewRepository.getAverageRatingForDoctor(doctorId);
        if(averageReview != null){
            return GetAverageReviewResponse.builder()
                    .averageReview(averageReview)
                    .build();
        }
        return null;
    }
}
