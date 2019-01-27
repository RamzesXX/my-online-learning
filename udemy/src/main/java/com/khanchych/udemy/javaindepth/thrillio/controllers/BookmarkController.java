package com.khanchych.udemy.javaindepth.thrillio.controllers;

import com.khanchych.udemy.javaindepth.thrillio.entities.Bookmark;
import com.khanchych.udemy.javaindepth.thrillio.entities.User;
import com.khanchych.udemy.javaindepth.thrillio.services.BookmarkService;

public class BookmarkController {
    private static BookmarkController instance = new BookmarkController();

    private BookmarkController() {
    }

    public static BookmarkController getInstance() {
        return instance;
    }

    public void saveUserBookmark(User user, Bookmark bookmark) {
        BookmarkService.getInstance().saveUserBookmark(user, bookmark);
    }
}
