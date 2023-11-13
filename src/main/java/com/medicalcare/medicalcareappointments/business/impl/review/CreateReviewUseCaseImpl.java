package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.business.CreateReviewUseCase;
import com.medicalcare.medicalcareappointments.business.impl.account.ReverseAccountConverter;
import com.medicalcare.medicalcareappointments.domain.review.CreateReviewRequest;
import com.medicalcare.medicalcareappointments.domain.review.CreateReviewResponse;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.PatientEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateReviewUseCaseImpl implements CreateReviewUseCase {

    private final ReviewRepository reviewRepository;

    @Override
    public CreateReviewResponse createReview(CreateReviewRequest request) {
        ReviewEntity savedReview = saveNewReview(request);

        return CreateReviewResponse.builder()
                .id(savedReview.getReviewId())
                .build();
    }

    private ReviewEntity saveNewReview(CreateReviewRequest request){
        ReviewEntity newReview = ReviewEntity.builder()
                .rating(request.getRating())
                .comment(request.getComment())
                .date(request.getDate())
                .patient((PatientEntity) ReverseAccountConverter.convert(request.getPatient()))
                .doctor((DoctorEntity) ReverseAccountConverter.convert(request.getDoctor()))
                .build();
        return reviewRepository.save(newReview);
    }
}
