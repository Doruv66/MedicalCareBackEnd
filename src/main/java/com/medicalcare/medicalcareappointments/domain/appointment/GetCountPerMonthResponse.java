package com.medicalcare.medicalcareappointments.domain.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCountPerMonthResponse {
    private Map<String, Integer> countMonth;
}
