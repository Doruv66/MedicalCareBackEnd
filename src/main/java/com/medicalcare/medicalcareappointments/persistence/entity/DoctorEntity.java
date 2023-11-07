package com.medicalcare.medicalcareappointments.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@DiscriminatorValue("Doctor")
@Table(name = "doctor")
@NoArgsConstructor
@AllArgsConstructor
public class DoctorEntity extends AccountEntity {
    @NotBlank
    @Length(min = 2, max = 255)
    @Column(name = "specialization")
    private String specialization;

    @NotBlank
    @Column(name = "photo")
    private String photo;

    @NotBlank
    @Length(min = 2, max = 255)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Length(min = 2, max = 255)
    @Column(name = "fname")
    private String fname;

    @Lob
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "doctor")
    private List<TimeSlotEntity> availableTimeSlots;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorEntity doctor = (DoctorEntity) o;
        return Objects.equals(specialization, doctor.specialization) &&
                Objects.equals(photo, doctor.photo) &&
                Objects.equals(name, doctor.name) &&
                Objects.equals(fname, doctor.fname) &&
                Objects.equals(description, doctor.description) &&
                Objects.equals(availableTimeSlots, doctor.availableTimeSlots);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specialization, photo, name, fname, description, availableTimeSlots);
    }

}
