package com.medicalcare.medicalcareappointments.domain.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetDoctorsResponse {
    private List<Account> accounts;
    private long accountsCount;
}
