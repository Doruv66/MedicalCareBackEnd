package com.medicalcare.medicalcareappointments.domain.account;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends Account {
    private String position;
}
