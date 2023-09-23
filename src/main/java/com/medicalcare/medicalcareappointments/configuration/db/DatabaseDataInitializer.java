package com.medicalcare.medicalcareappointments.configuration.db;

import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import com.medicalcare.medicalcareappointments.domain.appointment.AppointmentStatus;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
@AllArgsConstructor
public class DatabaseDataInitializer {

    private AccountRepository accountRepository;
    private ReviewRepository reviewRepository;
    private AppointmentRepository appointmentRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void populateWithDummyData() {
        accountRepository.save(DoctorEntity.builder().username("doctor").accountType(AccountType.Doctor).availableTimeSlots(new ArrayList<>()).Email("doctor@gmail.com").password("12345").specialization("dentist").build());
        accountRepository.save(UserEntity.builder().username("user").accountType(AccountType.User).Email("user@gmail.com").password("12345").firstName("user").lastName("resu").dateOfBirth(new Date()).build());
        accountRepository.save(AdminEntity.builder().username("admin").accountType(AccountType.Admin).Email("admin@gmail.com").password("12345").position("boss").build());
        reviewRepository.save(ReviewEntity.builder().comment("nice appointment").doctorId(1L).rating(5).userId(2L).build());
        reviewRepository.save(ReviewEntity.builder().comment("cool appointment").doctorId(1L).rating(4).userId(2L).build());
        reviewRepository.save(ReviewEntity.builder().comment("bad appointment").doctorId(1L).rating(1).userId(2L).build());
        appointmentRepository.save(AppointmentEntity.builder().appointmentStatus(AppointmentStatus.Pending).userId(2L).doctorId(1L).dateTime(new Date()).build());
        appointmentRepository.save(AppointmentEntity.builder().appointmentStatus(AppointmentStatus.Cancelled).userId(2L).doctorId(1L).dateTime(new Date()).build());
        appointmentRepository.save(AppointmentEntity.builder().appointmentStatus(AppointmentStatus.Confirmed).userId(2L).doctorId(1L).dateTime(new Date()).build());
    }
}
