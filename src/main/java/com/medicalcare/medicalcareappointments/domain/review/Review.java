package com.medicalcare.medicalcareappointments.domain.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private Long reviewId;
    private Date date;
    private int rating;
    private String comment;
    private Long userId;
    private Long doctorId;
}
