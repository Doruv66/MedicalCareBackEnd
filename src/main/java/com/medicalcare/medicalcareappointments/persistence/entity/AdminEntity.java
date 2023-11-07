package com.medicalcare.medicalcareappointments.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;



@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@DiscriminatorValue("ADMIN")
@Table(name = "admin")
@NoArgsConstructor
@AllArgsConstructor
public class AdminEntity extends AccountEntity {
    @NotBlank
    @Length(min = 2, max = 100)
    @Column(name = "position")
    private String position;
}