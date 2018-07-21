package com.khanchych.fridaytasks;//Given a string containing just the characters ‘(’ and ‘)’, find the length of the longest valid (well-formed) parentheses substring.
//
//    For “(()“, the longest valid parentheses substring is “()“, which has length = 2.
//
//    Another example is “)()())“, where the longest valid parentheses substring is “()()“, which has length = 4.

public class WellFormedParentheses {

    public int solve(String input) {
        int best = 0;

        return getBest(input, best);
    }

    private int getBest(String input, int best) {
        int sum = 0;
        int length = 0;
        int prevBest = 0;

        for (int i = 0; i < input.length(); i++) {
            if ('(' == input.charAt(i)) {
                sum++;
                continue;
            }

            sum--;

            if (sum < 0) {
                sum = length = 0;
                prevBest = best;
            } else {
                if (sum == 0) {
                    prevBest = best;
                }
                length += 2;
                if (best < length) {
                    best = length;
                }
            }
        }

        if (prevBest < (best - 2 * sum)) {
            best -= 2 * sum;
        }

        return best;
    }
}
