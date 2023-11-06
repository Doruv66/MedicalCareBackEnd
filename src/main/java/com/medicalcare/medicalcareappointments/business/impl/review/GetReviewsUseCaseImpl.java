package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.business.GetReviewsUseCase;
import com.medicalcare.medicalcareappointments.domain.review.GetReviewsResponse;
import com.medicalcare.medicalcareappointments.domain.review.Review;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetReviewsUseCaseImpl implements GetReviewsUseCase {

    private final ReviewRepository reviewRepository;

    @Transactional
    @Override
    public GetReviewsResponse getReviews() {
        GetReviewsResponse response = new GetReviewsResponse();
        List<Review> reviews = reviewRepository.findAll()
                .stream()
                .map(ReviewConverter::convert)
                .toList();
        response.setReviews(reviews);
        return response;
    }
}
