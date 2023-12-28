package com.medicalcare.medicalcareappointments.business.impl.appointment;

import com.medicalcare.medicalcareappointments.business.GetAppointmentsCountCurrentDateUseCase;
import com.medicalcare.medicalcareappointments.domain.account.Account;
import com.medicalcare.medicalcareappointments.domain.account.AccountType;
import com.medicalcare.medicalcareappointments.domain.appointment.GetAppointmentCountResponse;
import com.medicalcare.medicalcareappointments.persistence.AccountRepository;
import com.medicalcare.medicalcareappointments.persistence.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GetAppointmentsCountCurrentDateUseCaseImpl implements GetAppointmentsCountCurrentDateUseCase {

    private final AppointmentRepository appointmentRepository;

    private final AccountRepository accountRepository;

    @Override
    public GetAppointmentCountResponse getAppointmentCount() {
        int timeslots = 0;
        Optional<Integer> doctors = accountRepository.countAccountEntitiesByAccountType(AccountType.DOCTOR);
        if(doctors.isPresent()) {
            timeslots = doctors.get() * 9;
        }

        Optional<Integer> appointmentsCount = appointmentRepository.getCountOfAppointmentsByDay(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
        if(appointmentsCount.isEmpty()) {
            //throw an error
            return null;
        }
        return GetAppointmentCountResponse.builder()
                .totalCountOfAppointments(timeslots)
                .bookedAppointments(appointmentsCount.get())
                .build();
    }
}
