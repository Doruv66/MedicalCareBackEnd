package com.medicalcare.medicalcareappointments.domain.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAverageReviewResponse {
    private double averageReview;
}
