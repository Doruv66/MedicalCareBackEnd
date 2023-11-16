package com.medicalcare.medicalcareappointments.domain.account;

import com.medicalcare.medicalcareappointments.domain.timeslot.TimeSlot;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Objects;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Doctor extends Account {
    private String specialization;
    private String photo;
    private String description;
    private List<TimeSlot> availableTimeSlots;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(specialization, doctor.specialization) &&
                Objects.equals(photo, doctor.photo) &&
                Objects.equals(description, doctor.description) &&
                Objects.equals(availableTimeSlots, doctor.availableTimeSlots);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specialization, photo, description, availableTimeSlots);
    }
}

