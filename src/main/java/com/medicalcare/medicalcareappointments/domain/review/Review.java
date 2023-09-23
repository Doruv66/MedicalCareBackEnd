package com.medicalcare.medicalcareappointments.domain.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private Long reviewId;
    private int rating;
    private String comment;
    private Long userId;
    private Long doctorId;
}
