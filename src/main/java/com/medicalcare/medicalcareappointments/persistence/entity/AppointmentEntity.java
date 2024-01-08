package com.medicalcare.medicalcareappointments.persistence.entity;

import com.medicalcare.medicalcareappointments.domain.appointment.AppointmentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "appointment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long appointmentId;

    @NotNull
    @OneToOne
    @JoinColumn(name = "timeslot_id")
    private TimeSlotEntity timeSlot;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;

    @Enumerated(EnumType.STRING)
    @Column(name = "appointment_status")
    private AppointmentStatus appointmentStatus;
}
