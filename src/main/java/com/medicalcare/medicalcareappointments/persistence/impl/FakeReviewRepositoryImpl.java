package com.medicalcare.medicalcareappointments.persistence.impl;

import com.medicalcare.medicalcareappointments.persistence.ReviewRepository;
import com.medicalcare.medicalcareappointments.persistence.entity.ReviewEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeReviewRepositoryImpl implements ReviewRepository {

    private static long NEXT_ID = 1;

    private final List<ReviewEntity> savedReviews;

    public FakeReviewRepositoryImpl(){this.savedReviews = new ArrayList<>();}

    @Override
    public List<ReviewEntity> findAll() {
        return Collections.unmodifiableList(this.savedReviews);
    }

    @Override
    public ReviewEntity save(ReviewEntity review) {
        if(review.getReviewId() == null){
            review.setReviewId(NEXT_ID);
            NEXT_ID++;
            this.savedReviews.add(review);
        }
        return review;
    }

    @Override
    public void deleteById(long reviewId) {
        this.savedReviews.removeIf(review -> review.getReviewId().equals(reviewId));
    }

    @Override
    public Optional<ReviewEntity> findById(long reviewId) {
        return this.savedReviews.stream()
                .filter(review -> review.getReviewId().equals(reviewId))
                .findFirst();
    }
}
