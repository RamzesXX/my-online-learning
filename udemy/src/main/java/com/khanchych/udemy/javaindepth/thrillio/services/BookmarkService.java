package com.khanchych.udemy.javaindepth.thrillio.services;

import com.khanchych.udemy.javaindepth.thrillio.dao.BookmarkDao;
import com.khanchych.udemy.javaindepth.thrillio.entities.*;

public class BookmarkService {
    private static BookmarkService instance = new BookmarkService();
    private static BookmarkDao dao = new BookmarkDao();

    private BookmarkService() {
    }

    public static BookmarkService getInstance() {
        return instance;
    }

    public Movie createMovie(long id, String title, /*String profileUrl, */int releaseYear,
                             String[] cast, String[] directors, String genre, double imdbRating) {
        Movie movie = new Movie();

        movie.setId(id);
        movie.setTitle(title);
//        movie.setProfileUrl(profileUrl);
        movie.setReleaseYear(releaseYear);
        movie.setCast(cast);
        movie.setDirectors(directors);
        movie.setGenre(genre);
        movie.setImdbRating(imdbRating);

        return movie;
    }

    public Book createBook(long id, String title, /*String profileUrl, */int publicationYear, String publisher,
                           String[] authors, String genre, double amazonRating) {
        Book book = new Book();

        book.setId(id);
        book.setTitle(title);
//        book.setProfileUrl(profileUrl);
        book.setPublicationYear(publicationYear);
        book.setPublisher(publisher);
        book.setAuthors(authors);
        book.setGenre(genre);
        book.setAmazonRating(amazonRating);

        return book;
    }

    public WebLink createWebLink(long id, String title, String url, String host) {
        WebLink webLink = new WebLink();

        webLink.setId(id);
        webLink.setTitle(title);
//        webLink.setProfileUrl(profileUrl);
        webLink.setUrl(url);
        webLink.setHost(host);

        return webLink;
    }

    public Bookmark[][] getBookmarks() {
        return dao.getBookmarks();
    }

    public void saveUserBookmark(User user, Bookmark bookmark) {
        UserBookmark userBookmark = new UserBookmark();
        userBookmark.setUser(user);
        userBookmark.setBookmark(bookmark);
        dao.saveUserBookmark(userBookmark);
    }

    public void setKidFriendlyStatus(User user, String kidFriendlyStatus, Bookmark bookmark) {
        bookmark.setKidFriendlyStatus(kidFriendlyStatus);
        bookmark.setKidFriendlyMarkedBy(user);
        System.out.println("Kid Friendly Status: " + kidFriendlyStatus + "," + bookmark);
    }
}
