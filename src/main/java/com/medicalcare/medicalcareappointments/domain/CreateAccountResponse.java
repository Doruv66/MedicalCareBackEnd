package com.medicalcare.medicalcareappointments.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAccountResponse {
    private Long accountId;
}
