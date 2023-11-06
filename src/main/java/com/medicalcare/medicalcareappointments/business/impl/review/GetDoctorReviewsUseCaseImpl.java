package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.business.GetDoctorReviewsUseCase;
import com.medicalcare.medicalcareappointments.domain.review.GetReviewsResponse;
import com.medicalcare.medicalcareappointments.domain.review.Review;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetDoctorReviewsUseCaseImpl implements GetDoctorReviewsUseCase {

    private ReviewRepository reviewRepository;


    @Transactional
    @Override
    public GetReviewsResponse getDoctorReviews(long id) {
        List<Review> reviews = reviewRepository.findAll().stream()
                .filter(review -> review.getDoctor().getAccountId().equals(id))
                .map(ReviewConverter::convert)
                .toList();
        return GetReviewsResponse.builder()
                .reviews(reviews)
                .build();
    }
}
