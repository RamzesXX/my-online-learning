package com.khanchych.udemy.javaindepth.thrillio;

import com.khanchych.udemy.javaindepth.thrillio.constants.UserType;
import com.khanchych.udemy.javaindepth.thrillio.controllers.BookmarkController;
import com.khanchych.udemy.javaindepth.thrillio.entities.Bookmark;
import com.khanchych.udemy.javaindepth.thrillio.entities.User;

import static com.khanchych.udemy.javaindepth.thrillio.constants.KidFriendlyStatus.*;

public class View {
    public static void browse(User user, Bookmark[][] bookmarks) {
        System.out.println("\n" + user.getEmail() + " is browsing items ...");

        int bookmarkCount = 0;

        for (Bookmark[] bookmarkList : bookmarks) {
            for (Bookmark bookmark : bookmarkList) {
                if (bookmarkCount < DataStore.USER_BOOKMARK_LIMIT) {
                    boolean isBookmarked = getBookmarkDecision(bookmark);
                    if (isBookmarked) {
                        bookmarkCount++;
                        BookmarkController.getInstance().saveUserBookmark(user, bookmark);
                        System.out.println("New Item Bookmarked --- " + bookmark);
                    }
                }
                if((user.getUserType().equals(UserType.EDITOR) || user.getUserType().equals(UserType.CHIEF_EDITOR))
                        && bookmark.isKidFriendlyEligible()
                        && bookmark.getKidFriendlyStatus().equals(UNKNOWN)) {
                   BookmarkController.getInstance().setKidFriendlyStatus(user, getKidFriendlyDecision(bookmark), bookmark);
                }
            }
        }

    }

    private static String getKidFriendlyDecision(Bookmark bookmark) {
        return Math.random() < 0.5 ? APPROVED : REJECTED;
    }

    private static boolean getBookmarkDecision(Bookmark bookmark) {
        return Math.random() < 0.5;
    }

/*    public static void bookmark(User user, Bookmark[][] bookmarks) {
        System.out.println("\n" + user.getEmail() + " is bookmarking");
        for (int i = 0; i < DataStore.TOTAL_USER_COUNT; i++) {
            int typeOffset = (int) (Math.random() * DataStore.BOOKMARK_TYPES_COUNT);
            int bookmarkOffset = (int) (Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE);
            Bookmark bookmark = bookmarks[typeOffset][bookmarkOffset];
            BookmarkController.getInstance().saveUserBookmark(user, bookmark);
            System.out.println(bookmark);
        }

    }*/
}
