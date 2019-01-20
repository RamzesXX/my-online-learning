package com.khanchych.udemy.javaindepth.inheritence.thrill;

public class Movie extends Bookmark {
    private String directors;
    private String cast;

    public Movie(String title, String directors, String cast) {
        super(title);
        this.directors = directors;
        this.cast = cast;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }
}
