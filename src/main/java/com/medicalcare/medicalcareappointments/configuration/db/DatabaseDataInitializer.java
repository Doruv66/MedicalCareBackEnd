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

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@AllArgsConstructor
public class DatabaseDataInitializer {

    private AccountRepository accountRepository;
    private ReviewRepository reviewRepository;
    private AppointmentRepository appointmentRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void populateWithDummyData() {
        List<TimeSlotEntity> timeSlots = new ArrayList<>();

        for (int day = Calendar.MONDAY; day <= Calendar.FRIDAY; day++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_WEEK, day);

            for (int hour = 9; hour <= 17; hour++) {
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                Date start = calendar.getTime();

                calendar.add(Calendar.HOUR, 1);
                Date end = calendar.getTime();

                Timestamp startTime = new Timestamp(start.getTime());
                Timestamp endTime = new Timestamp(end.getTime());

                TimeSlotEntity timeSlot = new TimeSlotEntity(startTime, endTime);
                timeSlots.add(timeSlot);
            }
        }

        accountRepository.save(DoctorEntity.builder().photo("doctor").name("Emily").fname("Johnson").description("Dr. Emily Johnson is a board-certified Cardiologist with a passion for preventive cardiology. She is known for her pioneering research in heart disease prevention and her dedication to educating her patients about heart-healthy lifestyles. With over two decades of experience, Dr. Johnson has helped countless patients regain their heart health and lead fulfilling lives.").username("doctor").accountType(AccountType.Doctor).availableTimeSlots(timeSlots).Email("doctor@gmail.com").password("12345").specialization("Cardiologist").build());
        accountRepository.save(DoctorEntity.builder().photo("doctor1").name("Sarah").fname("Anderson").description("Dr. Sarah Anderson is a renowned Gynecologist specializing in reproductive health for women of all ages. With a warm and empathetic approach, she has guided women through the various stages of life, from adolescence to pregnancy and beyond. Dr. Anderson believes in the importance of women's health and strives to empower her patients with knowledge and personalized care.").username("doctor").accountType(AccountType.Doctor).availableTimeSlots(timeSlots).Email("doctor@gmail.com").password("12345").specialization("Gynecologist").build());
        accountRepository.save(DoctorEntity.builder().photo("doctor2").name("Michael").fname("Smith").description("Dr. Michael Smith is a leading Dermatologist known for his expertise in advanced skin care and cosmetic dermatology. He combines art and science to help his patients achieve their best skin. With a keen eye for aesthetics, Dr. Smith has transformed the lives of many, boosting their confidence through healthy and radiant skin.").username("doctor").accountType(AccountType.Doctor).availableTimeSlots(timeSlots).Email("doctor@gmail.com").password("12345").specialization("Dermatologist").build());
        accountRepository.save(DoctorEntity.builder().photo("doctor3").name("David").fname("Patel").description("Dr. David Patel is a compassionate Cardiologist committed to improving heart health in the community. He has a strong background in interventional cardiology and has performed life-saving procedures for many patients. Dr. Patel's holistic approach to patient care includes not only treating the heart but also addressing overall wellness.").username("doctor").accountType(AccountType.Doctor).availableTimeSlots(timeSlots).Email("doctor@gmail.com").password("12345").specialization("Cardiologist").build());
        accountRepository.save(DoctorEntity.builder().photo("doctor4").name("Michael").fname("Johnson").description("Dr. Michael Johnson is a dedicated Gynecologist who believes in the importance of women's reproductive health. He has a reputation for providing comprehensive care to women at every stage of life. Dr. Johnson's approach is marked by empathy and a commitment to improving the well-being of his patients.").username("doctor").accountType(AccountType.Doctor).availableTimeSlots(new ArrayList<>()).Email("doctor@gmail.com").password("12345").specialization("Gynecologist").build());
        accountRepository.save(DoctorEntity.builder().photo("doctor5").name("Emily").fname("Anderson").description("Dr. Emily Anderson is a leading Dermatologist who blends science and art in her practice. She is known for her innovative skincare solutions and her personalized approach to each patient's unique needs. Dr. Anderson's goal is to help her patients achieve not only healthy skin but also a renewed sense of confidence and well-being.").username("doctor").accountType(AccountType.Doctor).availableTimeSlots(timeSlots).Email("doctor@gmail.com").password("12345").specialization("Dermatologist").build());
        accountRepository.save(UserEntity.builder().username("user").accountType(AccountType.User).Email("user@gmail.com").password("12345").firstName("Vieru").lastName("Dorian").dateOfBirth(new Date()).build());
        accountRepository.save(AdminEntity.builder().username("admin").accountType(AccountType.Admin).Email("admin@gmail.com").password("12345").position("boss").build());

        reviewRepository.save(ReviewEntity.builder().comment("The appointment was an absolute delight. The doctor was attentive, and I left feeling extremely satisfied with the service.").date(Date.from(LocalDate.of(2023, 6, 6).atStartOfDay(ZoneId.systemDefault()).toInstant())).doctorId(1L).rating(5).userId(7L).build());
        reviewRepository.save(ReviewEntity.builder().comment("I had a fantastic experience during the appointment. The doctor's professionalism and care were evident throughout.").date(Date.from(LocalDate.of(2023, 6, 6).atStartOfDay(ZoneId.systemDefault()).toInstant())).doctorId(2L).rating(5).userId(7L).build());
        reviewRepository.save(ReviewEntity.builder().comment("Regrettably, the appointment did not meet my expectations. I encountered some issues that left me less than pleased.").date(Date.from(LocalDate.of(2023, 6, 6).atStartOfDay(ZoneId.systemDefault()).toInstant())).doctorId(3L).rating(5).userId(7L).build());
        reviewRepository.save(ReviewEntity.builder().comment("The appointment was decent, but there's room for improvement. Some aspects could have been handled better.").date(Date.from(LocalDate.of(2023, 6, 6).atStartOfDay(ZoneId.systemDefault()).toInstant())).doctorId(4L).rating(5).userId(7L).build());
        reviewRepository.save(ReviewEntity.builder().comment("The appointment was average, with a mix of positive and negative aspects. It was an okay experience.").date(Date.from(LocalDate.of(2023, 6, 6).atStartOfDay(ZoneId.systemDefault()).toInstant())).doctorId(5L).rating(5).userId(7L).build());
        reviewRepository.save(ReviewEntity.builder().comment("The appointment didn't meet my expectations. There were some disappointments that need to be addressed.").date(Date.from(LocalDate.of(2023, 6, 6).atStartOfDay(ZoneId.systemDefault()).toInstant())).doctorId(6L).rating(5).userId(7L).build());
        reviewRepository.save(ReviewEntity.builder().comment("The appointment was pleasant, but it could have been even better with some improvements.").date(Date.from(LocalDate.of(2023, 6, 6).atStartOfDay(ZoneId.systemDefault()).toInstant())).doctorId(1L).rating(4).userId(7L).build());
        reviewRepository.save(ReviewEntity.builder().comment("There were some issues during the appointment that could have been handled better. It was a satisfactory experience.").date(Date.from(LocalDate.of(2023, 6, 6).atStartOfDay(ZoneId.systemDefault()).toInstant())).doctorId(2L).rating(2).userId(7L).build());
        reviewRepository.save(ReviewEntity.builder().comment("The appointment was okay, but it didn't stand out as exceptional. There's potential for improvement.").date(Date.from(LocalDate.of(2023, 6, 6).atStartOfDay(ZoneId.systemDefault()).toInstant())).doctorId(3L).rating(4).userId(7L).build());
        reviewRepository.save(ReviewEntity.builder().comment("While the appointment had its positives, there were areas that left me wanting more. Improvement is needed.").date(Date.from(LocalDate.of(2023, 6, 6).atStartOfDay(ZoneId.systemDefault()).toInstant())).doctorId(4L).rating(2).userId(7L).build());
        reviewRepository.save(ReviewEntity.builder().comment("I expected a better experience during the appointment. It was just satisfactory, but not outstanding.").date(Date.from(LocalDate.of(2023, 6, 6).atStartOfDay(ZoneId.systemDefault()).toInstant())).doctorId(5L).rating(4).userId(7L).build());
        reviewRepository.save(ReviewEntity.builder().comment("The appointment had some shortcomings. There's potential for improvement in certain areas.").date(Date.from(LocalDate.of(2023, 6, 6).atStartOfDay(ZoneId.systemDefault()).toInstant())).doctorId(6L).rating(2).userId(7L).build());

        appointmentRepository.save(AppointmentEntity.builder().appointmentStatus(AppointmentStatus.Pending).userId(2L).doctorId(1L).dateTime(new Date()).build());
        appointmentRepository.save(AppointmentEntity.builder().appointmentStatus(AppointmentStatus.Cancelled).userId(2L).doctorId(1L).dateTime(new Date()).build());
        appointmentRepository.save(AppointmentEntity.builder().appointmentStatus(AppointmentStatus.Confirmed).userId(2L).doctorId(1L).dateTime(new Date()).build());
    }
}
