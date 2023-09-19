package com.medicalcare.medicalcareappointments.persistence.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AdminEntity extends AccountEntity{
    private String position;
}
