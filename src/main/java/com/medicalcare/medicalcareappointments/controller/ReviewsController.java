package com.medicalcare.medicalcareappointments.controller;

import com.medicalcare.medicalcareappointments.business.*;
import com.medicalcare.medicalcareappointments.domain.*;
import com.medicalcare.medicalcareappointments.exception.InvalidReviewException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
public class ReviewsController {

    private final GetReviewsUseCase getReviewsUseCase;
    private final GetReviewUseCase getReviewUseCase;
    private final DeleteReviewUseCase deleteReviewUseCase;
    private final CreateReviewUseCase createReviewUseCase;
    private final UpdateReviewUseCase updateReviewUseCase;

    @GetMapping("{id}")
    public ResponseEntity<Review> getReview(@PathVariable(value = "id") final long id) {
        final Optional<Review> reviewOptional = getReviewUseCase.getReview(id);
        if(reviewOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(reviewOptional.get());
    }

    @GetMapping
    public ResponseEntity<GetReviewsResponse> getReviews() {
        GetReviewsResponse response = getReviewsUseCase.getReviews();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable(value = "id") final long id) {
        deleteReviewUseCase.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CreateReviewResponse> createReview(@RequestBody @Valid CreateReviewRequest request) {
        CreateReviewResponse response = createReviewUseCase.createReview(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateReviewResponse> updateReview(@PathVariable(value = "id") final long id,
                                             @RequestBody @Valid UpdateReviewRequest request){
        UpdateReviewResponse response = updateReviewUseCase.updateReview(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
