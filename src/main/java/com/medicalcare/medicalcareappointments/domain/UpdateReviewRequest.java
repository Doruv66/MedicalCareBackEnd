package com.medicalcare.medicalcareappointments.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReviewRequest {

    private Long reviewId;

    @NotNull
    private int rating;

    @NotBlank
    private String comment;

    @NotNull
    private Long userId;

    @NotNull
    private Long doctorId;
}
