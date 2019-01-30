package com.khanchych.udemy.javaindepth.thrillio.entities;

import com.khanchych.udemy.javaindepth.thrillio.services.BookmarkService;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WebLinkTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.Test
    public void isKidFriendlyEligible() {
        // Test 1: porn in url -- false
        WebLink webLink = BookmarkService.getInstance().createWebLink(2000, "Taming Tiger, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-tiger--porn-2.html", "http://www.javaworld.com");
        boolean isKidFriendlyEligible = webLink.isKidFriendlyEligible();
        assertFalse("For porn in url isKidFriendlyEligible should return false", isKidFriendlyEligible);

        // Test 2: porn in title -- false
        webLink = BookmarkService.getInstance().createWebLink(2000, "Taming Tiger, Part 2 porn", "http://www.javaworld.com/article/2072759/core-java/taming-tiger-2.html", "http://www.javaworld.com");
        isKidFriendlyEligible = webLink.isKidFriendlyEligible();
        assertFalse("For porn in title isKidFriendlyEligible should return false", isKidFriendlyEligible);

        // Test 3: adult in host -- false
        webLink = BookmarkService.getInstance().createWebLink(2000, "Taming Tiger, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-tiger-2.html", "http://www.adult.com");
        isKidFriendlyEligible = webLink.isKidFriendlyEligible();
        assertFalse("For adult in host isKidFriendlyEligible should return false", isKidFriendlyEligible);

        // Test 4: adult in url but not in host -- true
        webLink = BookmarkService.getInstance().createWebLink(2000, "Taming Tiger, Part 2", "http://www.javaworld.com/article/2072759/core-java/taming-tiger-adult-2.html", "http://www.javaworld.com");
        isKidFriendlyEligible = webLink.isKidFriendlyEligible();
        assertTrue("For adult in url but not in host isKidFriendlyEligible should return true", isKidFriendlyEligible);

        // Test 5: adult in title only -- true
        webLink = BookmarkService.getInstance().createWebLink(2000, "Taming Tiger, Part 2 adult", "http://www.javaworld.com/article/2072759/core-java/taming-tiger-2.html", "http://www.javaworld.com");
        isKidFriendlyEligible = webLink.isKidFriendlyEligible();
        assertTrue("For adult in title only isKidFriendlyEligible should return true", isKidFriendlyEligible);


    }
}