package com.medicalcare.medicalcareappointments.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@DiscriminatorValue("PATIENT")
@Table(name = "patient")
@NoArgsConstructor
@AllArgsConstructor
public class PatientEntity extends AccountEntity {
    @Column(name = "date_of_birth")
    private Timestamp dateOfBirth;
}
