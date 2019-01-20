package com.khanchych.udemy.javaindepth.inheritence.thrill;

public abstract class Bookmark {
    private int id;
    private String title;
    private String description;
    private String profileUrl;

    public Bookmark(String title) {
        this.title = title;
    }
}
