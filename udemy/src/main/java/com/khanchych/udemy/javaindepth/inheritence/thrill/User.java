package com.khanchych.udemy.javaindepth.inheritence.thrill;

public abstract class User {
    public void rateBookmark() {
    }

    public Review postAReview(String reviewText) {
        return new Review(reviewText);
    }
}
