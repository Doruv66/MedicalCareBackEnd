package com.medicalcare.medicalcareappointments.persistence.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ReviewEntity {
    private Long reviewId;
    private Date date;
    private int rating;
    private String comment;
    private Long userId;
    private Long doctorId;
}
