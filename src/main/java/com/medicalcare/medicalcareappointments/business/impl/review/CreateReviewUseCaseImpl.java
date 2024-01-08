package com.medicalcare.medicalcareappointments.business.impl.review;

import com.medicalcare.medicalcareappointments.business.CreateReviewUseCase;
import com.medicalcare.medicalcareappointments.business.impl.account.ReverseAccountConverter;
import com.medicalcare.medicalcareappointments.business.impl.timeslot.ReverseTimeSlotConverter;
import com.medicalcare.medicalcareappointments.domain.appointment.AppointmentStatus;
import com.medicalcare.medicalcareappointments.domain.review.CreateReviewRequest;
import com.medicalcare.medicalcareappointments.domain.review.CreateReviewResponse;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.AppointmentEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.DoctorEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.PatientEntity;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateReviewUseCaseImpl implements CreateReviewUseCase {

    private final ReviewRepository reviewRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    public CreateReviewResponse createReview(CreateReviewRequest request) {
        ReviewEntity savedReview = saveNewReview(request);
        updateReviewStatus(request);
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

    private void updateReviewStatus(CreateReviewRequest request) {
        AppointmentEntity app = AppointmentEntity.builder()
                .doctor((DoctorEntity) ReverseAccountConverter.convert(request.getAppointment().getDoctor()))
                .appointmentStatus(AppointmentStatus.REVIEWED)
                .appointmentId(request.getAppointment().getAppointmentId())
                .patient((PatientEntity) ReverseAccountConverter.convert(request.getAppointment().getPatient()))
                .timeSlot(ReverseTimeSlotConverter.convert(request.getAppointment().getTimeSlot()))
                .build();
        appointmentRepository.save(app);
    }
}
