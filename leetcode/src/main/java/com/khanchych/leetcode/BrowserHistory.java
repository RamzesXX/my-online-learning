package com.khanchych.leetcode;

import java.util.*;

class BrowserHistory {
    List<String> history = new ArrayList<>();
    int current = -1;
    int last = -1;

    public BrowserHistory(String homepage) {
        visit(homepage);
    }

    public void visit(String url) {
        current++;
        if (current >= history.size()) {
            history.add(url);
        } else {
            history.set(current, url);
        }
        last = current;
    }

    public String back(int steps) {
        current = Math.max(0, current - steps);
        return history.get(current);
    }

    public String forward(int steps) {
        current = Math.min(last, current + steps);
        return history.get(current);

    }
}
