package com.medicalcare.medicalcareappointments.persistence.entity;

import com.medicalcare.medicalcareappointments.domain.account.TimeSlot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "time_slot")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_slot_id")
    private Long timeSlotId;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;
}
