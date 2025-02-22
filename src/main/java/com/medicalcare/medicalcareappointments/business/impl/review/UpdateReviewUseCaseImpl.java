package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.business.UpdateReviewUseCase;
import com.medicalcare.medicalcareappointments.business.impl.account.ReverseAccountConverter;
import com.medicalcare.medicalcareappointments.domain.review.UpdateReviewRequest;
import com.medicalcare.medicalcareappointments.domain.review.UpdateReviewResponse;
import com.medicalcare.medicalcareappointments.exception.NotFoundReviewException;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.PatientEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateReviewUseCaseImpl implements UpdateReviewUseCase {

    private final ReviewRepository reviewRepository;

    @Override
    public UpdateReviewResponse updateReview(UpdateReviewRequest request, long id) {
        Optional<ReviewEntity> optionalReview = reviewRepository.findById(id);
        if(optionalReview.isEmpty()){
            throw new NotFoundReviewException("NOT_FOUND_REVIEW");
        }
        ReviewEntity review = optionalReview.get();
        updateFields(request, review);
        return UpdateReviewResponse.builder()
                .id(review.getReviewId())
                .build();
    }

    private void updateFields(UpdateReviewRequest request, ReviewEntity review){
        review.setComment(request.getComment());
        review.setRating(request.getRating());
        review.setDate(request.getDate());
        review.setDoctor((DoctorEntity) ReverseAccountConverter.convert(request.getDoctor()));
        review.setPatient((PatientEntity) ReverseAccountConverter.convert(request.getPatient()));
        reviewRepository.save(review);
    }
}
