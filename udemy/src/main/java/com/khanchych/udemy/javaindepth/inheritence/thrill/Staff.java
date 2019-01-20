package com.khanchych.udemy.javaindepth.inheritence.thrill;

public class Staff extends User {

    @Override
    public Review postAReview(String reviewText) {
        Review review = super.postAReview(reviewText);

        review.setApproved(true);

        return review;
    }
}
