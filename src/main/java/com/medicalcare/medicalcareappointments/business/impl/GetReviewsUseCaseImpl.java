package com.medicalcare.medicalcareappointments.business.impl;

import com.medicalcare.medicalcareappointments.business.GetReviewsUseCase;
import com.medicalcare.medicalcareappointments.domain.GetReviewsResponse;
import com.medicalcare.medicalcareappointments.domain.Review;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetReviewsUseCaseImpl implements GetReviewsUseCase {

    private final ReviewRepository reviewRepository;
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
