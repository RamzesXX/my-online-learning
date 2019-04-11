package com.khanchych.udemy.javaindepth.thrillio;


import com.khanchych.udemy.javaindepth.thrillio.constants.Gender;
import com.khanchych.udemy.javaindepth.thrillio.entities.Bookmark;
import com.khanchych.udemy.javaindepth.thrillio.entities.User;
import com.khanchych.udemy.javaindepth.thrillio.entities.UserBookmark;
import com.khanchych.udemy.javaindepth.thrillio.services.BookmarkService;
import com.khanchych.udemy.javaindepth.thrillio.services.UserService;
import com.khanchych.udemy.javaindepth.thrillio.util.IOUtil;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    private static List<User> users = new ArrayList<>();
    public static List<User> getUsers() {
        return users;
    }

    private static List<List<Bookmark>> bookmarks =new ArrayList<>();
    public static List<List<Bookmark>> getBookmarks() {
        return bookmarks;
    }

    private static List<UserBookmark> userBookmarks = new ArrayList<>();

    public static void loadData() {
        loadUsers();
        loadWebLinks();
        loadMovies();
        loadBooks();
    }

    private static void loadUsers() {
        List<String> data = new ArrayList<>();

        IOUtil.read(data, "./src/main/resources/User.txt");
        for (String row : data) {
            String[] values = row.split("\t");

            int gender = Gender.MALE;
            if (values[5].equals("f")) {
                gender = Gender.FEMALE;
            } else if (values[5].equals("t")) {
                gender = Gender.TRANSGENDER;
            }

            User user = UserService.getInstance().createUser(Long.parseLong(values[0]), values[1], values[2], values[3], values[4], gender, values[6]);
            users.add(user);
        }
    }

    private static void loadWebLinks() {
        List<String> data = new ArrayList<>();
        List<Bookmark> webLinks = new ArrayList<>();
        IOUtil.read(data, "./src/main/resources/Web-Link.txt");
        for (String row : data) {
            String[] values = row.split("\t");
            Bookmark webLink = BookmarkService.getInstance().createWebLink(Long.parseLong(values[0]), values[1], values[2], values[3]/*, values[4]*/);
            webLinks.add(webLink);
        }
        bookmarks.add(webLinks);
    }

    private static void loadMovies() {
        List<String> data = new ArrayList<>();
        List<Bookmark> movies = new ArrayList<>();
        IOUtil.read(data, "./src/main/resources/Movie.txt");
        for (String row : data) {
            String[] values = row.split("\t");
            String[] cast = values[3].split(",");
            String[] directors = values[4].split(",");
            Bookmark movie = BookmarkService.getInstance().createMovie(Long.parseLong(values[0]), values[1], "",Integer.parseInt(values[2]), cast, directors, values[5], Double.parseDouble(values[6])/*, values[7]*/);
            movies.add(movie);
        }
        bookmarks.add(movies);
    }

    private static void loadBooks() {
        List<String> data = new ArrayList<>();
        List<Bookmark> books = new ArrayList<>();
        IOUtil.read(data, "./src/main/resources/Movie.txt");
        for (String row : data) {
            String[] values = row.split("\t");
            String[] authors = values[4].split(",");
            Bookmark book = BookmarkService.getInstance().createBook(Long.parseLong(values[0]), values[1], Integer.parseInt(values[2]), values[3], authors, values[5], Double.parseDouble(values[6])/*, values[7]*/);
            books.add(book);
        }
        bookmarks.add(books);
    }

    public static void add(UserBookmark userBookmark) {
        userBookmarks.add(userBookmark);
    }
}
