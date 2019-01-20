package com.khanchych.udemy.javaindepth.inheritence.thrill;

public class Book extends Bookmark implements Sharable {
    private String authors;

    public Book(String title, String authors) {
        super(title);
        this.authors = authors;
    }

    @Override
    public String getItemData() {
        return null;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }
}
