package com.khanchych.udemy.javaindepth.inheritence.thrill;

public class Editor extends Staff {
    public Review approveReview(Review review) {
        review.setApproved(true);

        return review;
    }

    public Review rejectReview(Review review) {
        review.setApproved(false);

        return review;
    }
}
