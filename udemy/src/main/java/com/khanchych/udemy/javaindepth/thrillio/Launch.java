package com.khanchych.udemy.javaindepth.thrillio;


import com.khanchych.udemy.javaindepth.thrillio.entities.Bookmark;
import com.khanchych.udemy.javaindepth.thrillio.entities.User;
import com.khanchych.udemy.javaindepth.thrillio.services.BookmarkService;
import com.khanchych.udemy.javaindepth.thrillio.services.UserService;

public class Launch {
    private static User[] users;
    private static Bookmark[][] bookmarks;

    private static void loadData() {
        System.out.println("1. Loading Data");
        DataStore.loadData();

        users = UserService.getInstance().getUsers();
        bookmarks = BookmarkService.getInstance().getBookmarks();

        System.out.println("Printing loaded data");
        printUserData();
        printBookMarkData();
    }

    private static void printBookMarkData() {
        for (Bookmark[] bookmarkList :bookmarks) {
            for (Bookmark bookmark: bookmarkList) {
                System.out.println(bookmark);
            }
        }
    }

    private static void printUserData() {
        for (User user :users) {
            System.out.println(user);
        }
    }

    public static void main(String[] args) {
        loadData();
        startBookmarking();
    }

    private static void startBookmarking() {
        System.out.println("2. Bookmarking Data");
        for (User user : users) {
            View.bookmark(user, bookmarks);
        }
    }
}