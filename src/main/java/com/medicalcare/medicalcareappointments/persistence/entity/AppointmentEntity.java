package com.medicalcare.medicalcareappointments.persistence.entity;

import com.medicalcare.medicalcareappointments.domain.AppointmentStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AppointmentEntity {private Long appointmentId;
    private Date dateTime;
    private Long userId;
    private Long doctorId;
    private AppointmentStatus appointmentStatus;

}
