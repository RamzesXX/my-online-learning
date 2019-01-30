package com.khanchych.udemy.javaindepth.thrillio.entities;

import com.khanchych.udemy.javaindepth.thrillio.constants.BookGenre;
import com.khanchych.udemy.javaindepth.thrillio.constants.MovieGenre;
import com.khanchych.udemy.javaindepth.thrillio.services.BookmarkService;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieTest {

    @Test
    public void isKidFriendlyEligible() {

        // Test 1: THRILLERS -- false
        Bookmark movie = BookmarkService.getInstance().createMovie(3000, "Citizen Kane", 1941, new String[]{"Orson Welles", "Joseph Cotten"}, new String[]{"Orson Welles"}, MovieGenre.THRILLERS, 8.5);
        boolean isKidFriendlyEligible = movie.isKidFriendlyEligible();
        assertFalse("For THRILLERS  isKidFriendlyEligible should return false", isKidFriendlyEligible);

        // Test 2: HORROR -- false
        movie = BookmarkService.getInstance().createMovie(3000, "Citizen Kane", 1941, new String[]{"Orson Welles", "Joseph Cotten"}, new String[]{"Orson Welles"}, MovieGenre.HORROR, 8.5);
        isKidFriendlyEligible = movie.isKidFriendlyEligible();
        assertFalse("For HORROR  isKidFriendlyEligible should return false", isKidFriendlyEligible);

    }
}