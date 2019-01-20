package com.khanchych.udemy.javaindepth.inheritence.thrill;

public class WebLink extends Bookmark implements Sharable {
    public WebLink(String title) {
        super(title);
    }

    @Override
    public String getItemData() {
        return null;
    }
}
