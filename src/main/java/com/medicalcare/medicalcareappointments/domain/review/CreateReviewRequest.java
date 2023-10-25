package com.medicalcare.medicalcareappointments.domain.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewRequest {

    @NotNull
    private int rating;

    @NotBlank
    private String comment;

    @NotNull
    private Long userId;

    @NotNull
    private Date date;

    @NotNull
    private Long doctorId;
}
