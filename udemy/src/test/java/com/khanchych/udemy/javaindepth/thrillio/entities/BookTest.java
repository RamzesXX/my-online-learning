package com.khanchych.udemy.javaindepth.thrillio.entities;

import com.khanchych.udemy.javaindepth.thrillio.constants.BookGenre;
import com.khanchych.udemy.javaindepth.thrillio.services.BookmarkService;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {

    @Test
    public void isKidFriendlyEligible() {
        // Test 1: PHILOSOPHY -- false
        Bookmark book = BookmarkService.getInstance().createBook(4000, "Walden", 1854, "Wilder Publications", new String[]{"Henry David Thoreau"}, BookGenre.PHILOSOPHY, 4.3);
        boolean isKidFriendlyEligible = book.isKidFriendlyEligible();
        assertFalse("For PHILOSOPHY  isKidFriendlyEligible should return false", isKidFriendlyEligible);

        // Test 2: SELF_HELP -- false
        book = BookmarkService.getInstance().createBook(4000, "Walden", 1854, "Wilder Publications", new String[]{"Henry David Thoreau"}, BookGenre.SELF_HELP, 4.3);
        isKidFriendlyEligible = book.isKidFriendlyEligible();
        assertFalse("For SELF_HELP  isKidFriendlyEligible should return false", isKidFriendlyEligible);

    }
}