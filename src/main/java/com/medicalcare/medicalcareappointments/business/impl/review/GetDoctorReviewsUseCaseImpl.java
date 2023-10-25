package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.business.GetDoctorReviewsUseCase;
import com.medicalcare.medicalcareappointments.domain.review.GetReviewsResponse;
import com.medicalcare.medicalcareappointments.domain.review.Review;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetDoctorReviewsUseCaseImpl implements GetDoctorReviewsUseCase {

    private ReviewRepository reviewRepository;

    @Override
    public GetReviewsResponse getDoctorReviews(long id) {
        List<Review> reviews = reviewRepository.findAll().stream()
                .filter(review -> review.getDoctorId().equals(id))
                .map(ReviewConverter::convert)
                .toList();
        return GetReviewsResponse.builder()
                .reviews(reviews)
                .build();
    }
}
