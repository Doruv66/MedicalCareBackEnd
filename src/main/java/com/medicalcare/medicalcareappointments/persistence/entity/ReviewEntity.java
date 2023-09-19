package com.medicalcare.medicalcareappointments.persistence.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewEntity {
    private Long reviewId;
    private int rating;
    private String comment;
    private Long userId;
    private Long doctorId;
}
