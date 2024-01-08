package com.medicalcare.medicalcareappointments.persistence.entity;

import com.medicalcare.medicalcareappointments.domain.timeslot.TimeSlotType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "timeslot_type")
    private TimeSlotType timeSlotType;
}
