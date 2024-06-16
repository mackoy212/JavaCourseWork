package com.example.ServiceExchange.service;

import com.example.ServiceExchange.model.Review;
import com.example.ServiceExchange.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(int id) {
        return reviewRepository.findById(id);
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review updateReview(int id, Review updatedReview) {
        Optional<Review> existingReviewOptional = reviewRepository.findById(id);
        if (existingReviewOptional.isPresent()) {
            Review existingReview = existingReviewOptional.get();
            existingReview.setRating(updatedReview.getRating());
            existingReview.setText(updatedReview.getText());
            return reviewRepository.save(existingReview);
        } else {
            throw new RuntimeException("Review not found with id: " + id);
        }
    }

    public void deleteReview(int id) {
        reviewRepository.deleteById(id);
    }
}
