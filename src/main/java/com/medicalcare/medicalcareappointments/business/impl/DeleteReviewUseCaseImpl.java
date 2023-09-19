package com.medicalcare.medicalcareappointments.business.impl;

import com.medicalcare.medicalcareappointments.business.DeleteReviewUseCase;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteReviewUseCaseImpl implements DeleteReviewUseCase {

    private final ReviewRepository reviewRepository;

    @Override
    public void deleteReview(long id) {
        reviewRepository.deleteById(id);
    }
}
