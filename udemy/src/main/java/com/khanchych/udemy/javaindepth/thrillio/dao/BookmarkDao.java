package com.khanchych.udemy.javaindepth.thrillio.dao;

import com.khanchych.udemy.javaindepth.thrillio.entities.Bookmark;
import com.khanchych.udemy.javaindepth.thrillio.DataStore;
import com.khanchych.udemy.javaindepth.thrillio.entities.UserBookmark;

import java.util.List;

public class BookmarkDao {
    public List<List<Bookmark>> getBookmarks(){
        return DataStore.getBookmarks();
    }

    public void saveUserBookmark(UserBookmark userBookmark) {
        DataStore.add(userBookmark);
    }
}
