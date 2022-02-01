package com.khanchych.leetcode;

public class MaximumNumberOfWordsFoundInSentences {
    public int mostWordsFound(String[] sentences) {
        int maxNumberOfWords = 0;
        for(String sentence: sentences) {
            maxNumberOfWords = Math.max(maxNumberOfWords, sentence.split("\\s").length);
        }
        return maxNumberOfWords;
    }
}
