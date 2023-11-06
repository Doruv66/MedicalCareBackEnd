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
}
