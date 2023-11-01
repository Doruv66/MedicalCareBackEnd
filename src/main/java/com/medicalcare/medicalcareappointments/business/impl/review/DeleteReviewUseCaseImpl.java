package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.business.DeleteReviewUseCase;
import com.medicalcare.medicalcareappointments.exception.NotFoundReviewException;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteReviewUseCaseImpl implements DeleteReviewUseCase {
    //comment
    private final ReviewRepository reviewRepository;

    @Override
    public void deleteReview(long id) {
        if(reviewRepository.findById(id).isEmpty()) {
            throw new NotFoundReviewException("NOT_FOUND_REVIEW");
        }
        reviewRepository.deleteById(id);
    }
}
